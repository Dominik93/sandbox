import time
from datetime import datetime
from enum import Enum


class Level(Enum):
    INFO = 1
    DEBUG = 2
    OFF = 3


class LogPrinter:

    def __init__(self, level, name):
        self.level = level
        self.name = name

    def now(self):
        now = datetime.now()
        return now.strftime('%Y-%m-%dT%H:%M:%S.%f')

    def print(self, log_format, content_provider):
        pass


class ConsoleLogPrinter(LogPrinter):

    def __init__(self, level, name):
        super().__init__(level, name)

    def print(self, log_format, content_provider):
        print(f'{self.level.name}:{self.now()}:{self.name}:{content_provider(log_format)}')


class FileLogPrinter(LogPrinter):

    def __init__(self, level, name, file_name):
        super().__init__(level, name)
        self.file_name = file_name

    def print(self, log_format, content_provider):
        log_file = open(self.file_name, "a")
        log_file.write(f'{self.level.name}:{self.now()}:{self.name}:{content_provider(log_format)}\n')
        log_file.close()


class CompositeLogPrinter(LogPrinter):

    def __init__(self, level, name):
        super().__init__(level, name)
        self.printers = [ConsoleLogPrinter(level, name), FileLogPrinter(level, name, "app.log")]

    def print(self, log_format, content_provider):
        if log_format is None:
            return
        if self.level.value < Level.DEBUG.value:
            log_format = log_format.replace("{args}", "-").replace("{result}", "-")
        if self.level != Level.OFF:
            for printer in self.printers:
                printer.print(log_format, content_provider)

    def _get_log(self, exclude_levels, log_provider):
        if self.level in exclude_levels:
            return ""
        return log_provider()


def log(level=Level.INFO,
        start_message="Execution started args: {args}",
        end_message="Execution completed args: {args} result: {result} in {duration}ms"):
    def log_decorator(func):
        def log_wrapper(*args, **kwargs):
            printer = CompositeLogPrinter(level, func.__name__)
            printer.print(start_message, lambda x: x.format(args=args))

            start = time.time_ns()
            result = func(*args, **kwargs)
            duration = _get_duration(start)

            printer.print(end_message, lambda x: x.format(args=args, result=f"{str(result)}", duration=duration))
            return result

        return log_wrapper

    return log_decorator


def _get_duration(start):
    return _convert_to_ms(time.time_ns() - start)


def _convert_to_ms(ns):
    return int(ns / 1000000)
