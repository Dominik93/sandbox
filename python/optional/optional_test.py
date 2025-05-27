import unittest

from optional import of, empty


class OptionalTestCase(unittest.TestCase):

    def test_should_get_value_if_present(self):
        self.assertEqual("sample", of("sample").get())

    def test_should_raise_exception_when_get_value_if_absent(self):
        self.assertRaises(Exception, lambda: empty().get())

    def test_should_get_other_value_if_absent(self):
        self.assertEqual("other", empty().or_get("other"))

    def test_should_map_value(self):
        self.assertEqual("sample-mapped", of("sample").map(lambda x: x + "-mapped").get())

    def test_should_get_other_value_by_callable(self):
        self.assertEqual("other", empty().or_else_get(lambda : "other"))


if __name__ == '__main__':
    unittest.main()
