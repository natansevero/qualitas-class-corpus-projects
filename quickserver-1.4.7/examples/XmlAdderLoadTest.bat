@rem Args: [client_count] [host] [port]
@java -Dxmladder.XmlAdderClient.bebug=false -Dxmladder.XmlAdderClient.brokenReq=false -cp %classpath%;.\dist\xmladder.jar;.\..\dist\commons-digester.jar;.\..\dist\commons-collections.jar;.\..\dist\commons-logging.jar;.\..\dist\commons-beanutils.jar; xmladder.LoadTest %1 %2 %3