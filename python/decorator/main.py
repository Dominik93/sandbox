import time

DEBUG = True


def log(debug):
    def log_decorator(func):
        def log_wrapper(*args, **kwargs):
            start = time.time_ns()
            log_args = args if debug else ""
            print(f'Execution started {func.__name__}{log_args}')

            result = func(*args, **kwargs)

            log_result = f" returned: {str(result)}" if debug else ""
            duration = int((time.time_ns() - start) / 1000000)
            print(f'Execution completed {func.__name__}{log_args}{log_result} in {duration}ms')

        return log_wrapper

    return log_decorator


@log(DEBUG)
def sample():
    time.sleep(1)
    pass


@log(DEBUG)
def process_parameters(one, two):
    time.sleep(1)
    pass


@log(DEBUG)
def get_value():
    time.sleep(1)
    return "sample"


if __name__ == '__main__':
    sample()
    process_parameters("one", 5)
    get_value()
