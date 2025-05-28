import time
from datetime import datetime
from enum import Enum


class Level(Enum):
    INFO = 1
    DEBUG = 2
    OFF = 3


class Logger:

    def __init__(self, level: Level, name: str):
        self.level = level
        self.name = name

    def now(self):
        now = datetime.now()
        return now.strftime('%Y-%m-%dT%H:%M:%S.%f')

    def log(self, log_format: str, content_provider):
        pass


class ConsoleLogger(Logger):

    def __init__(self, level: Level, name: str):
        super().__init__(level, name)

    def log(self, log_format: str, content_provider):
        print(f'{self.level.name}:{self.now()}:{self.name}:{content_provider(log_format)}')


class FileLogger(Logger):

    def __init__(self, level: Level, name: str, file_name: str):
        super().__init__(level, name)
        self.file_name = file_name

    def log(self, log_format: str, content_provider):
        log_file = open(self.file_name, "a")
        log_file.write(f'{self.level.name}:{self.now()}:{self.name}:{content_provider(log_format)}\n')
        log_file.close()


class CompositeLogger(Logger):

    def __init__(self, level: Level, name: str):
        super().__init__(level, name)
        self.printers = [ConsoleLogger(level, name), FileLogger(level, name, "app.log")]

    def log(self, log_format: str, content_provider=lambda x: x):
        if log_format is None:
            return
        if self.level.value < Level.DEBUG.value:
            log_format = log_format.replace("{args}", "-").replace("{result}", "-")
        if self.level != Level.OFF:
            for printer in self.printers:
                printer.log(log_format, content_provider)

    def _get_log(self, exclude_levels: list[Level], log_provider):
        if self.level in exclude_levels:
            return ""
        return log_provider()


def log(level=Level.INFO,
        start_message="Execution started args: {args}",
        end_message="Execution completed args: {args} result: {result} in {duration}ms"):
    def log_decorator(func):
        def log_wrapper(*args, **kwargs):
            printer = CompositeLogger(level, func.__name__)
            printer.log(start_message, lambda x: x.format(args=args))

            start = time.time_ns()
            result = func(*args, **kwargs)
            duration = _get_duration(start)

            printer.log(end_message, lambda x: x.format(args=args, result=f"{str(result)}", duration=duration))
            return result

        return log_wrapper

    return log_decorator


def _get_duration(start):
    return _convert_to_ms(time.time_ns() - start)


def _convert_to_ms(ns):
    return int(ns / 1000000)
