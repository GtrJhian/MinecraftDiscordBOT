# Minecraft Discord Bot

### Description
A simple minecraft and discord integration. Send messages from minecraft server to discord channels and vice versa.


**jdk:** 1.8

**current-version:** 1.0.0

### How to use
| feature | how |
| --- | --- |
| notifications | automatic once channel ids are configured |
| message from mc server to discord | use @discord prefix when writing a message in game, eg: (@discord hello from minecraft server) |
| message from discord to mc server| mention your bot followed by the message, eg: (@YourMCBot hello from discord server)|


### Build

**Maven**
```bash
    # make sure jdk and maven is properly configured
    mvn clean compile package
```
**Using make**
```bash
    # make sure make, maven and jdk is properly configured
    make build
```

### Configuration
```yaml
# show debug logs
debug: false

# discord bot configuration
discord:

   # your discord bot token
   # see: https://discord.com/developers/applications
   #      https://discord.com/developers/docs/topics/oauth2#bots
   token: vy5fr2qUnf1facxOZM1JTQ==
   		
   # list of channels to send messages
   # take note of the quotation marks(")
   channels:
    - "123456789"
    - "696969696969"

# discord usernames of administrators (not used yet)
ops:
- Sparky014
- zljian13
```

### Contribute
A docker-compose file is already configured to run a local development environment setup.

**Running with docker-compose**
```bash
docker-compose up
```
**Make**
```bash
# execute after making a change, to copy the jar file into the data directory
make build
# after applying the configuration you can now run the local minecraft server with the plugin
make up
```