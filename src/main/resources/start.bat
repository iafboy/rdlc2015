@ECHO OFF
@ECHO STARTUP App
FOR %%F IN (%cd%\*.jar) DO call :addcp %%F
goto extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
goto :eof
:extlibe
SET CLASSPATH=%CLASSPATH%;%cd%
SET CLASSPATH
java -Dapp.cfg=simpleAppSrvCfg.properties -DsimpleAppSrv.cachespace=C:\work\workspace\rdlc\SimpleAppSrv_SVN\cache com.expocms.server.launcher.HTTPServer
pause