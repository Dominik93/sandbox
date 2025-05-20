import os
import random
import time

from executor import AsyncExecutor, SyncExecutor


def _process(item, delay):
    print(f"{os.getpid()}: process {item} with {delay}")
    time.sleep(delay)
    return item * delay


if __name__ == "__main__":
    print("\nExecute asynchronous")
    asyncExecutor = AsyncExecutor()
    asyncExecutor.add(_process, [1, random.randint(1, 9)])
    asyncExecutor.add(_process, [2, random.randint(1, 9)])
    asyncExecutor.add(_process, [3, random.randint(1, 9)])
    results = asyncExecutor.execute()
    print(str(results))

    print("\nExecute synchronous")
    syncExecutor = SyncExecutor()
    syncExecutor.add(_process, [1, random.randint(1, 9)])
    syncExecutor.add(_process, [2, random.randint(1, 9)])
    syncExecutor.add(_process, [3, random.randint(1, 9)])
    results = syncExecutor.execute()
    print(str(results))
