import time
from datetime import datetime
from enum import Enum


class Level(Enum):
    INFO = 1
    DEBUG = 2
    OFF = 3


class Logger:

    def __init__(self, level: Level, name: str, timestamp_format="%Y-%m-%dT%H:%M:%S.%f"):
        self.level = level
        self.name = name
        self.timestamp_format = timestamp_format

    def now(self):
        return datetime.now().strftime(self.timestamp_format)

    def get_name(self, method_name):
        return f'{self.name}:{method_name}' if method_name is not None else self.name

    def log(self, method_name, log_format: str, content_provider=lambda x: x):
        pass

    def format(self, method_name, log_format: str, content_provider=lambda x: x):
        return f'{self.level.name}:{self.now()}:{self.get_name(method_name)}:{content_provider(log_format)}'


class ConsoleLogger(Logger):

    def __init__(self, level: Level, name: str):
        super().__init__(level, name)

    def log(self, method_name, log_format: str, content_provider=lambda x: x):
        print(self.format(method_name, log_format, content_provider))


class FileLogger(Logger):

    def __init__(self, level: Level, name: str, file_name: str):
        super().__init__(level, name)
        self.file_name = file_name

    def log(self, method_name, log_format: str, content_provider=lambda x: x):
        log_file = open(self.file_name, "a")
        log_file.write(self.format(method_name, log_format, content_provider) + '\n')
        log_file.close()


class CompositeLogger(Logger):

    def __init__(self, level: Level, name: str):
        super().__init__(level, name)
        self.printers = [ConsoleLogger(level, name), FileLogger(level, name, "app.log")]

    def log(self, method_name=None, log_format: str = "", content_provider=lambda x: x):
        if log_format is None:
            return
        if self.level.value < Level.DEBUG.value:
            log_format = log_format.replace("{args}", "-").replace("{result}", "-")
        if self.level != Level.OFF:
            for printer in self.printers:
                printer.log(method_name, log_format, content_provider)


def log(level=Level.INFO,
        start_message="Execution started args: {args}",
        end_message="Execution completed args: {args} result: {result} in {duration}ms"):
    def log_decorator(func):
        def log_wrapper(*args, **kwargs):
            printer = CompositeLogger(level, "Log")
            printer.log(method_name=func.__name__, log_format=start_message,
                        content_provider=lambda x: x.format(args=args))

            start = time.time_ns()
            result = func(*args, **kwargs)
            duration = _get_duration(start)

            printer.log(method_name=func.__name__, log_format=end_message,
                        content_provider=lambda x: x.format(args=args, result=f"{str(result)}", duration=duration))
            return result

        return log_wrapper

    return log_decorator


def _get_duration(start):
    return _convert_to_ms(time.time_ns() - start)


def _convert_to_ms(ns):
    return int(ns / 1000000)
