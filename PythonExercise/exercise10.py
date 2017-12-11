
from matplotlib.pyplot import figure, show
from numpy import arange, sin, pi

n = arange(0.0, 2.0, 0.01)

fig = figure(1)

ax1 = fig.add_subplot(211)
ax1.plot(n, sin(sin(n - 2)) * 2.8 ** (-n ** 2))
ax1.grid(True)
ax1.set_ylim((-2, 2))
ax1.set_ylabel('y-axis')
ax1.set_xlabel('x-axis')
ax1.set_title('Exercise 10')

for label in ax1.get_xticklabels():
    label.set_color('b')

show()
