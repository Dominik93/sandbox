import time

from logger import log, Level

level = Level.INFO


@log(level)
def sample():
    time.sleep(1)
    pass


@log(level)
def process_parameters(one, two):
    time.sleep(1)
    pass


@log(level)
def get_value():
    time.sleep(1)
    return "sample"


if __name__ == '__main__':
    sample()
    process_parameters("one", 5)
    get_value()
