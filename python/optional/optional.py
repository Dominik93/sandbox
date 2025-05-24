def of(value):
    return Optional(value)


def empty():
    return Optional(None)


class Optional:

    def __init__(self, value):
        self.value = value

    def map(self, mapper):
        if self.value is None:
            return Optional(None)
        return Optional(mapper(self.value))

    def get(self):
        if self.value is None:
            raise Exception("Value is None")
        return self.value

    def or_get(self, other):
        return other if self.value is None else self.value

    def or_else_get(self, other_provider):
        return other_provider() if self.value is None else self.value

    def or_else_throw(self):
        if self.value is None:
            raise Exception("Value is None")
        return self.value
