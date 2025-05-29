import time

from logger import log, Level, set_root_level

level = Level.DEBUG


@log(level)
def sample():
    time.sleep(1)
    pass


@log(level)
def process_parameters(one, two):
    time.sleep(1)
    pass


@log(level)
def process_parameter(one):
    time.sleep(1)
    pass


@log(level)
def get_value():
    time.sleep(1)
    return "sample"


@log(level, start_message="Start get sample", end_message="Completed in {duration}ms")
def sample_message():
    time.sleep(1)
    pass


@log(level, start_message=None, end_message="Executed")
def none_message():
    time.sleep(1)
    pass


def execute(level):
    print("----")
    set_root_level(level)
    sample()
    process_parameters("one", 5)
    process_parameter("one")
    value = get_value()
    sample_message()
    none_message()
    print("----")


if __name__ == '__main__':
    execute(Level.OFF)
    execute(Level.INFO)
    execute(Level.DEBUG)
    execute(Level.ALL)
