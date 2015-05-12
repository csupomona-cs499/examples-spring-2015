# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import commands
import sys
import os


# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
# device.installPackage('myproject/bin/MyApplication.apk')

# sets a variable with the package's internal name
package = 'com.tinder'

# sets a variable with the name of an Activity in the package
activity = 'com.tinder.activities.ActivitySplashLoading'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)

# Presses the Menu button
# device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)

MonkeyRunner.sleep(3)

for x in xrange(1,5):    
    result = device.takeSnapshot()    
    fileName = '/Users/yusun/Desktop/shot' + str(x) + '.png'
    result.writeToFile(fileName,'png')

    size = os.path.getsize(fileName)
    print size
    if (size > 800000) :
        device.drag((70,500), (590,500), 1, 5)
    else :
        device.drag((590,500), (70,500), 1, 5)
    MonkeyRunner.sleep(2)


# Takes a screenshot
# result = device.takeSnapshot()

# Writes the screenshot to a file
result.writeToFile('/Users/yusun/Desktop/shot1.png','png')