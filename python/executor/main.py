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
    async_executor = AsyncExecutor()
    async_executor.add(_process, [1, random.randint(1, 9)])
    async_executor.add(_process, [2, random.randint(1, 9)])
    async_executor.add(_process, [3, random.randint(1, 9)])
    results = async_executor.execute()
    print(str(results))

    print("\nExecute synchronous")
    sync_executor = SyncExecutor()
    sync_executor.add(_process, [1, random.randint(1, 9)])
    sync_executor.add(_process, [2, random.randint(1, 9)])
    sync_executor.add(_process, [3, random.randint(1, 9)])
    results = sync_executor.execute()
    print(str(results))
