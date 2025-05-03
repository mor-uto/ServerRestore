# ServerRestore
a tool to quickly restore back your discord server when it gets termed.

### Setup
1. make an options.yml file in the location of ServerRestore.jar, and set the inside of it like this
and fill it out as you need
```yaml
token: ...
guildid: ...

restoreOperations:
  restoreChannels: true
  restorePredefinedMessages: true
  restoreRoles: true

serverCategories:
  Information:
    - "\uD83D\uDCD8｜rules-and-tos"
    - "\uD83D\uDCE2｜announcements"
    - "\uD83D\uDCB3｜payments"
    - "\uD83E\uDD1D｜partners"
    - "✅｜vouches"
    - "\uD83C\uDFAB｜tickets"
    - "\uD83C\uDF81｜giveaway"
  General:
    - "💬｜chat"
  Stock:
    - "\uD83D\uDCE6｜minecraft"
    - "\uD83D\uDCE6｜capes"
    - "\uD83D\uDCE6｜streaming-products"
    - "\uD83D\uDCE6｜disc0rd-services"
    - "\uD83C\uDF81｜rewards"
  Accounts: []

predefinedMessages:
  message1:
    channel: "\uD83D\uDCB3｜payments"
    message: "Accepting LTC Only!"
roles:
  Owner:
    displaySeperately: true
    anyoneMention: false
```
