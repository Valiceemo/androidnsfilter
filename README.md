# androidnsfilter

Forked from https://github.com/IngoZenz/personaldnsfilter<br>
All credit to the Dev here

Attempts to allow Tasker to start / stop the DNS filter service, via an intent receiver
I am no coder, but my Google Fu is strong, and Gemini did the work

Tasker can now start and stop DNS filtering, via a send intent action:

START:
```
action: dnsfilter.android.action.START_FROM_TASKER
package: dnsfilter.android
class: dnsfilter.android.TaskerReceiver
```
STOP:
```
action: dnsfilter.android.action.STOP_FROM_TASKER
package: dnsfilter.android
class: dnsfilter.android.TaskerReceiver
```
It is also possible to use the Run Shell action in Tasker, with Shizuku, using the command:
```
am broadcast -a "dnsfilter.android.action.START_FROM_TASKER" -p "dnsfilter.android"
```
```
am broadcast -a "dnsfilter.android.action.STOP_FROM_TASKER" -p "dnsfilter.android"
```

Before the intents will work, you must first grant the app the permission to create a local VPN, this is normal, and is a one time click the first time the app is opened.<br>
As far as I can make out, all other app functions are the same, and working as expected.
