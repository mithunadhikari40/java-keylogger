### Disclaimer:
This script is intended for educational purposes only. The developer is not responsible for any misuse or illegal activities involving this code. Use this script at your own risk.

### Introduction
This Java  is a keylogger, designed to record keystrokes entered on a system. It's important to note that using keyloggers without proper consent or for malicious purposes is illegal and unethical.

### Legal and Ethical Considerations
**Consent**: Always obtain explicit consent from individuals before using a keylogger on their devices.<br>
**Privacy**: Respect privacy laws and regulations.<br>
**Malicious Use**: Avoid using keyloggers for harmful purposes, such as identity theft or unauthorized access. <br>
**Remember**: Using keyloggers without proper authorization is illegal and can have serious consequences. This script is provided solely for educational purposes.<br>

### How to build and run 
1. In intellij, go to build -> build artifacts and click on keylogger.jar <br><br>
2. Go to the folder where the jar is created, it's usually inside the out folder:
 
    `cd out/artifacts/keylogger_jar` <br><br>
3. Run the jar file:
 
    `java -classpath keylogger.jar -Djava.home=$JAVA_HOME -jar keylogger.jar`<br><br>
4. You don't have to include `-Djava.home=$JAVA_HOME` if your jdk is set up properly and `JAVA_HOME` points to your jdk installation.


