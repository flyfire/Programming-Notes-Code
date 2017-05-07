# 演示Python中的时间

import time  # 时间

print(time.time())
print(time.localtime(time.time()))
print(time.asctime(time.localtime(time.time())))
