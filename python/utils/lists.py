from typing import Callable

from optional.optional import Optional, empty, of


def find_item(items: list[any], predicate: Callable) -> Optional:
    for item in items:
        if predicate(item):
            return of(item)
    return empty()
