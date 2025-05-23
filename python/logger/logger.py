import time
from enum import Enum


class Level(Enum):
    INFO = 1
    DEBUG = 2
    TRACE = 3
    OFF = 4


def log(level):
    def log_decorator(func):
        def log_wrapper(*args, **kwargs):
            start = time.time_ns()

            log_args = _get_log(level, [Level.INFO], lambda: args)
            _print(level, f'Execution started {func.__name__}{log_args}')

            result = func(*args, **kwargs)

            log_result = _get_log(level, [Level.INFO], lambda: f" returned: {str(result)}")
            duration = int((time.time_ns() - start) / 1000000)
            _print(level, f'Execution completed {func.__name__}{log_args}{log_result} in {duration}ms')

        return log_wrapper

    return log_decorator


def _print(level, content):
    if level != Level.OFF:
        print(content)


def _get_log(level, exclude_levels, log_provider):
    if level in exclude_levels:
        return ""
    return log_provider()
