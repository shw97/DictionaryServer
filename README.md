# DictionaryServer
A client server architecture for dictionary based operations.
The client can search for a word, add a new word or delete an existing word.
The addition and deletion of a word is reflected across all clients connected to the dictionary server.
## Commands
Server Side: java -jar DictionaryServer.jar {port-no} {Dictionary-file}
##
Client Side: java -jar DictionaryClient.jar {ip-address} {port-no}
