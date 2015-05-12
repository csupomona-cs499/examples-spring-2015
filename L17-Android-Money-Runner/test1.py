# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
# device.installPackage('myproject/bin/MyApplication.apk')

# sets a variable with the package's internal name
package = 'edu.cpp.cs499.l13_data_storage'

# sets a variable with the name of an Activity in the package
activity = 'edu.cpp.cs499.l13_data_storage.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)

# Presses the Menu button
# device.press('KEYCODE_MENU', MonkeyDevice.DOWN_AND_UP)

device.touch(400, 362, MonkeyDevice.DOWN_AND_UP)
device.type('project1')
MonkeyRunner.sleep(2)

device.touch(400, 472, MonkeyDevice.DOWN_AND_UP)
device.type('project1')
MonkeyRunner.sleep(2)

device.touch(400, 582, MonkeyDevice.DOWN_AND_UP)
device.type('1010')
MonkeyRunner.sleep(2)

device.touch(310, 716, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
device.touch(726, 728, MonkeyDevice.DOWN_AND_UP)

# Takes a screenshot
result = device.takeSnapshot()

# Writes the screenshot to a file
result.writeToFile('/Users/yusun/Desktop/shot1.png','png')