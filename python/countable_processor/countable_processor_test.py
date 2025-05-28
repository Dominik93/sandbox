import unittest

from countable_processor import CountableProcessor, ExceptionStrategy


def _interrupt(item: str):
    if item == "2":
        raise Exception
    return item


ASK = ExceptionStrategy.ASK
PASS = ExceptionStrategy.PASS
INTERRUPT = ExceptionStrategy.INTERRUPT


class CountableProcessorTestCase(unittest.TestCase):

    def test_should_process_items(self):
        results = []
        CountableProcessor(lambda x: results.append(x)).run(["1", "2", "3"])
        self.assertEqual(["1", "2", "3"], results)

    def test_should_process_and_return_items(self):
        results = CountableProcessor(lambda x: x).run(["1", "2", "3"])
        self.assertEqual(["1", "2", "3"], results)

    def test_should_process_items_interrupt(self):
        results = []
        CountableProcessor(lambda x: results.append(_interrupt(x)), strategy=INTERRUPT).run(
            ["1", "2", "3"])
        self.assertEqual(["1"], results)

    def test_should_process_and_return_items_interrupt(self):
        results = CountableProcessor(lambda x: _interrupt(x), strategy=INTERRUPT).run(["1", "2", "3"])
        self.assertEqual(["1"], results)

    def test_should_process_items_pass(self):
        results = []
        CountableProcessor(lambda x: results.append(_interrupt(x)), strategy=PASS).run(
            ["1", "2", "3"])
        self.assertEqual(["1", "3"], results)

    def test_should_process_and_return_items_pass(self):
        results = CountableProcessor(lambda x: _interrupt(x), strategy=PASS).run(["1", "2", "3"])
        self.assertEqual(["1", "3"], results)

    def test_should_process_items_ask_yes(self):
        results = []
        CountableProcessor(lambda x: results.append(_interrupt(x)), lambda: "Y", ASK).run(["1", "2", "3"])
        self.assertEqual(["1", "3"], results)

    def test_should_process_and_return_items_ask_yes(self):
        results = CountableProcessor(lambda x: _interrupt(x), lambda: "Y", ASK).run(["1", "2", "3"])
        self.assertEqual(["1", "3"], results)

    def test_should_process_items_ask_no(self):
        results = []
        CountableProcessor(lambda x: results.append(_interrupt(x)), lambda: "N", ASK).run(["1", "2", "3"])
        self.assertEqual(["1"], results)

    def test_should_process_and_return_items_ask_no(self):
        results = CountableProcessor(lambda x: _interrupt(x), lambda: "N", ASK).run(["1", "2", "3"])
        self.assertEqual(["1"], results)


if __name__ == '__main__':
    unittest.main()
