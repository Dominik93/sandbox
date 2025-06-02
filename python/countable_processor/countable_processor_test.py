import unittest

from countable_processor import CountableProcessor, ExceptionStrategy


def _interrupt(item: str):
    if item == "2":
        raise Exception("Test exception")
    return item


ASK = ExceptionStrategy.ASK
PASS = ExceptionStrategy.PASS
INTERRUPT = ExceptionStrategy.INTERRUPT


class CountableProcessorTestCase(unittest.TestCase):

    def test_should_process_items(self):
        results = []
        CountableProcessor(["1", "2", "3"]).run(lambda x: results.append(x))
        self.assertEqual(["1", "2", "3"], results)

    def test_should_process_and_return_items(self):
        results = CountableProcessor(["1", "2", "3"]).run(lambda x: x)
        self.assertEqual(["1", "2", "3"], results)

    def test_should_process_items_interrupt(self):
        results = []
        CountableProcessor(["1", "2", "3"]).run(
            lambda x: results.append(_interrupt(x)), exception_strategy=INTERRUPT)
        self.assertEqual(["1"], results)

    def test_should_process_and_return_items_interrupt(self):
        results = CountableProcessor(["1", "2", "3"]).run(lambda x: _interrupt(x), exception_strategy=INTERRUPT)
        self.assertEqual(["1"], results)

    def test_should_process_items_pass(self):
        results = []
        CountableProcessor( ["1", "2", "3"]).run(lambda x: results.append(_interrupt(x)), exception_strategy=PASS )
        self.assertEqual(["1", "3"], results)

    def test_should_process_and_return_items_pass(self):
        results = CountableProcessor(["1", "2", "3"]).run(lambda x: _interrupt(x), exception_strategy=PASS)
        self.assertEqual(["1", "3"], results)

    def test_should_process_items_ask_yes(self):
        results = []
        CountableProcessor(["1", "2", "3"]).run(lambda x: results.append(_interrupt(x)), lambda: "Y", ASK)
        self.assertEqual(["1", "3"], results)

    def test_should_process_and_return_items_ask_yes(self):
        results = CountableProcessor(["1", "2", "3"]).run(lambda x: _interrupt(x), lambda: "Y", ASK)
        self.assertEqual(["1", "3"], results)

    def test_should_process_items_ask_no(self):
        results = []
        CountableProcessor(["1", "2", "3"]).run(lambda x: results.append(_interrupt(x)), lambda: "N", ASK)
        self.assertEqual(["1"], results)

    def test_should_process_and_return_items_ask_no(self):
        results = CountableProcessor(["1", "2", "3"]).run(lambda x: _interrupt(x), lambda: "N", ASK)
        self.assertEqual(["1"], results)


if __name__ == '__main__':
    unittest.main()
