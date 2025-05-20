import time

from countable_processor import CountableProcessor, ExceptionStrategy


def _process(item, error=None):
    time.sleep(1)
    print(f'process item {item}')
    if item == error:
        raise Exception('Error')


def _convert(item, error=None):
    time.sleep(1)
    if item == error:
        raise Exception('Error')
    return item + '-processed'


if __name__ == "__main__":
    print('\nRun with void method:')
    CountableProcessor(lambda x: _process(x)).run(["1", "2", "3"])

    print('\nRun with method returning object:')
    print(str(CountableProcessor(lambda x: _convert(x)).run(["1", "2", "3"])))

    print('\nException INTERRUPT with void method:')
    CountableProcessor(lambda x: _process(x, "2"), ExceptionStrategy.INTERRUPT).run(["1", "2", "3"])

    print('\nException INTERRUPT with method returning object:')
    print(str(CountableProcessor(lambda x: _convert(x, "2"), ExceptionStrategy.INTERRUPT).run(["1", "2", "3"])))

    print('\nException PASS with void method:')
    CountableProcessor(lambda x: _process(x, "2"), ExceptionStrategy.PASS).run(["1", "2", "3"])

    print('\nException PASS with method returning object:')
    print(str(CountableProcessor(lambda x: _convert(x, "2"), ExceptionStrategy.PASS).run(["1", "2", "3"])))
