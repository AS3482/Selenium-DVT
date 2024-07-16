import pymysql
import os

top = open("top.txt", 'r')
imports = top.read()
mid = open("mid.txt", 'r')
testMethod = mid.read()
second_mid = open("second_mid.txt", 'r')
exceptionHandling = second_mid.read()
last = open("last.txt", 'r')
closefile = last.read()
projectPath = "C:/selenium/moneybadgerWebportal/src/test/java/test/C"


def caseID(CID,printerID,dependency,userID):    
    print("this is the case ID "+str(CID))
    #database call for getting variables at case level  
    db = pymysql.connect(host='10.170.244.3',
                        database='AEMS',
                        user='zbrzcatusr',
                        password='ZbrZc@TU$r03')
    Execute=db.cursor()                              
    Execute.callproc("Selenium_Tests.ExecEngine_PathVariables",[CID,printerID,0,dependency,userID])
    results1=Execute.fetchall()           
    for i in range(len(results1)):
        res1=results1[i]
        if res1[0] is not None:
            filePathVariable = "C:/selenium/moneybadgerWebportal/variables/"
            filePathVariable = os.path.join(filePathVariable, str(CID))             
            try: 
                os.makedirs(filePathVariable, exist_ok = True) 
                print("Directory '%s' created successfully" % CID) 
            except OSError as error: 
                print("Directory '%s' can not be created" % CID)                               
            f2=open(filePathVariable+'/@'+res1[0], 'wb')                         
            f2.write(res1[1])                    
            f2.close()
        else:
            print("Their are no case level variables")
    #database call for getting test case verion    
    dbtc = pymysql.connect(host='10.170.244.3',
                     database='AEMS',
                     user='zbrzcatusr',
                     password='ZbrZc@TU$r03') 
    ex=dbtc.cursor()
    sql = "SELECT Selenium_Tests.Retreive_Version("+str(CID)+","+str(printerID)+","+str(0)+","+str(userID)+")"
    ex.execute(sql)   
    #write
    f1=open(projectPath+str(CID)+".java", 'w')
    lines=[imports+"\n", #below is name of the java file top and below should be same
    "C"+str(CID)+"\n", 
    testMethod+"\n",
    "test(ITestContext testContext)\n",
    exceptionHandling+"\n",
    'tcVersion ="'+str(ex.fetchone()[0])+'";\n']
    f1.writelines(lines)
    f1.close()
    db = pymysql.connect(host='10.170.244.3',
                     database='AEMS',
                     user='zbrzcatusr',
                     password='ZbrZc@TU$r03')
    Execute=db.cursor()
    Execute.callproc("Selenium_Tests.ExecEngine_ExecuteCase", [CID,printerID,0,dependency,userID])
    results=Execute.fetchall()        
    for i in range(len(results)):
        res_main=results[i]
        stepID = res_main[8]
        stepData = res_main[1]
        #print(i)
        #match stepID:
        if stepID==8: #setUP              
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines1=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n', 
                    'testContext.setAttribute("section", section);\n',
                    'api_call.startSetUp(execID, stepID,stepName);\n']
                f1.writelines(lines1)                   
                f1.close()
                print("this is 8 setup "+section)               
                print("this is 8 setup "+str(res_main[8]))
        if stepID==7: #Main              
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines1=[
                    'testContext.setAttribute("stop_watch_setup",watch.elapsed(TimeUnit.SECONDS));\n',
                    'watch = Stopwatch.createStarted();\n'
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'api_call.startMain(execID);\n']        
                    
                f1.writelines(lines1)                   
                f1.close()
                print("this is 7 Main "+section)               
                print("this is 7 Main "+str(res_main[8]))
        if stepID==9: #cleanUP              
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines1=[
                    'testContext.setAttribute("stop_watch_main",watch.elapsed(TimeUnit.SECONDS));\n',
                    'watch = Stopwatch.createStarted();\n',
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'api_call.startCleanUp(execID, stepID,stepName);\n']                    
                f1.writelines(lines1)                   
                f1.close()
                print("this is 9 cleanup "+section)               
                print("this is 9 cleanup"+str(res_main[8]))
        if stepID==235: #Launch Browser
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_235=Execute.fetchall()        
                for i in range(len(results_235)):
                    res_235=results_235[i]
                    browser = res_235[3]
                    if browser == "chrome":
                        browserLine="genericMethods zc = new genericMethods(driver_chrome);"
                    elif browser =="Firefox":
                        browserLine="genericMethods zf = new genericMethods(driver_firefox);"
                    elif browser =="Edge":
                        browserLine="genericMethods ze = new genericMethods(driver_edge);" 
                    elif browser =="Safari":
                        browserLine="genericMethods zs = new genericMethods(driver_safari);" 
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines1=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'z.setBrowser("'+browser+'");\n',
                    browserLine+'\n'
                    ]
                if section=="Setup":
                    lines11=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 235 "+section)
                elif section == "Cleanup":
                    lines11= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 235 "+section)
                else :
                    lines11=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 235 "+section)                     
                print("this is 235 "+str(res_main[8]))       
                
        if stepID==236: # set URL
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_236=Execute.fetchall()        
                for i in range(len(results_236)):
                    res_236=results_236[i]
                    url = res_236[3]
                stepID= res_main[1] 
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines2=[     
                        'stepID = "'+str(stepID)+'";\n',
                        'stepTypeName = "'+stepTypeName+'";\n',
                        'stepName = "'+stepName+'";\n',
                        'ordinalNum = ordinalNum+1;\n', 
                        'section = "'+section+'";\n',
                        'testContext.setAttribute("stepID", stepID);\n'
                        'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                        'testContext.setAttribute("stepName", stepName);\n',
                        'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                        'testContext.setAttribute("section", section);\n',
                        'z.getURL("'+url+'");\n'
                        ]
                if section=="Setup":
                    lines21=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines2)
                    f1.writelines(lines21)
                    f1.close()
                    print("this is 236 "+section)
                elif section == "Cleanup":
                    lines21= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines2)
                    f1.writelines(lines21)
                    f1.close()
                    print("this is 236 "+section)
                else :
                    lines21=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines2)
                    f1.writelines(lines21)
                    f1.close()
                    print("this is 236 "+section)                                      
                print("this is 236 "+str(res_main[8]))
                
        if stepID==237: #close driver
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_235=Execute.fetchall()        
                for i in range(len(results_235)):
                    res_235=results_235[i]
                    browser = res_235[3]
                    if browser == "chrome":
                         browserClose="zc.closeBrowser();"
                    elif browser =="Firefox":
                         browserClose="zf.closeBrowser();"
                    elif browser =="Edge":
                         browserClose="ze.closeBrowser();" 
                    elif browser =="Safari":
                         browserClose="zs.closeBrowser();"
                    elif browser == "closeAll":
                         browserClose="zc.closeBrowser();\nze.closeBrowser();\nzf.closeBrowser();" 
                    else:
                         browserClose="z.closeBrowser();"
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines3=[                                
                        'stepID = "'+str(stepID)+'";\n',
                        'stepTypeName = "'+stepTypeName+'";\n',
                        'stepName = "'+stepName+'";\n',
                        'ordinalNum = ordinalNum+1;\n', 
                        'section = "'+section+'";\n',
                        'testContext.setAttribute("stepID", stepID);\n'
                        'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                        'testContext.setAttribute("stepName", stepName);\n',
                        'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                        'testContext.setAttribute("section", section);\n', 
                        browserClose+'\n'
                        ]
                if section == "Setup":
                    lines31=['api_call.startSetUp(exedfhldkfh;ldskcID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n',
                        closefile+'\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 237 "+section)
                elif section == "Cleanup":                
                    lines31=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n',
                        closefile+'\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 237 "+section)
                else :
                    lines31=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n',
                        closefile+'\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 237 "+section)                                     
                print("this is 237 "+str(res_main[8]))                                 
              
        if stepID==238: #click action
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            element = res_238[3]
                        case 1:
                            locator =  res_238[3]                 
                stepID= res_main[1] 
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines4=[
                        'stepID = "'+str(stepID)+'";\n',
                        'stepTypeName = "'+stepTypeName+'";\n',
                        'stepName = "'+stepName+'";\n',
                        'ordinalNum = ordinalNum+1;\n', 
                        'section = "'+section+'";\n',                        
                        'testContext.setAttribute("stepID", stepID);\n'
                        'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                        'testContext.setAttribute("stepName", stepName);\n',
                        'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                        'testContext.setAttribute("section", section);\n',
                        'z.Click("'+locator+'", "'+element.strip()+'");\n'
                        ]
                if section=="Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 238 "+section)
                elif section == "Cleanup":
                    lines41= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 238 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 238 "+section)                        
                print("this is 238 "+ str(res_main[8]))
             
        if stepID==239: #Send text
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_239=Execute.fetchall()        
                for i in range(len(results_239)):
                    res_239=results_239[i]
                    match i:
                        case 0:
                            element = res_239[3]
                        case 1:
                            locator =  res_239[3] 
                        case 2:
                            text = res_239[3]                                
                            if text is not None:
                                text = res_239[3]
                            else:
                                text=""                            
              
                stepID= res_main[1]     
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]                
                f1 = open(projectPath+str(CID)+".java","a")
                lines5=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'z.sendText("'+locator+'", "'+element.strip()+'", "'+text.strip()+'");\n'
                    ]
                if section=="Setup":
                    lines51=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines5)
                    f1.writelines(lines51)
                    f1.close()
                    print("this is 239 "+section)
                elif section == "Cleanup":
                    lines51= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines5)
                    f1.writelines(lines51)
                    f1.close()
                    print("this is 239 "+section)
                else :
                    lines51=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines5)
                    f1.writelines(lines51)
                    f1.close()
                    print("this is 239 "+section)                    
                print("this is 239 "+str(res_main[8])) 
        if stepID==240: #get title and compare
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_240=Execute.fetchall()        
                for i in range(len(results_240)):
                    res_240=results_240[i]
                    match i:
                        case 0:
                            expectedTitle = res_240[3]
                            if expectedTitle is not None:
                                expectedTitle = res_240[3]
                            else:
                                expectedTitle=""
                        case 1:
                            expectedURL = res_240[3] 
                            if expectedURL is not None:
                                expectedURL = res_240[3]
                            else:
                                expectedURL=""
                        case 2:
                            option = res_240[3] 
                            if option is not None:
                                option = res_240[3]
                            else:
                                option=""
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines6=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'z.getAndComparePage("'+expectedTitle+'","'+expectedURL+'", "'+option+'");\n'
                    ]
                if section=="Setup":
                    lines61=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines6)
                    f1.writelines(lines61)
                    f1.close()
                    print("this is 240 "+section)
                elif section == "Cleanup":
                    lines61= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines6)
                    f1.writelines(lines61)
                    f1.close()
                    print("this is 240 "+section)
                else :
                    lines61=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines6)
                    f1.writelines(lines61)
                    f1.close()
                    print("this is 240 "+section)          
                print("this is 240 "+str(res_main[8]))                  
      
        if stepID==241: #get text   
                stepID= res_main[1] 
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines7=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',                    
                    'z.getText("'+locator+'", "'+element.strip()+'");\n'
                    ]
                if section=="Setup":
                    lines71=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines7)
                    f1.writelines(lines71)
                    f1.close()
                    print("this is 241 "+section)
                elif section == "Cleanup":
                    lines71= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines7)
                    f1.writelines(lines71)
                    f1.close()
                    print("this is 241 "+section)
                else :
                    lines71=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines7)
                    f1.writelines(lines71)
                    f1.close()
                    print("this is 241 "+section)                   
                print("this is 241 "+str(res_main[8]))        
               
        if stepID==242: #get text and compare
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            element = res_242[3]                            
                        case 1:
                            expected = res_242[3] 
                            expected= expected.replace('"', '\\"')                           
                        case 2:
                            locator =  res_242[3]
                            
                stepID= res_main[1]     
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'z.getTextCompare("'+locator+'", "'+element.strip()+'", "'+expected.strip()+'");\n'
                    ]
                if section=="Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 242 "+section)
                elif section == "Cleanup":
                    lines81= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 242 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 242 "+section)                    
                print("this is 242 "+str(res_main[8])) 
        if stepID==258: #get title and url of new opened tab
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_258=Execute.fetchall()        
                for i in range(len(results_258)):
                    res_258=results_258[i]
                    match i:
                        case 0:
                            title = res_258[3]
                        case 1:
                            url = res_258[3]                       
                            
                stepID= res_main[1]     
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]                
                f1 = open(projectPath+str(CID)+".java","a")
                lines9=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'z.nextTab("'+url+'", "'+title+'");\n'
                    ]
                if section=="Setup":
                    lines91=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines9)
                    f1.writelines(lines91)
                    f1.close()
                    print("this is 258 "+section)
                elif section == "Cleanup":
                    lines91= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines9)
                    f1.writelines(lines91)
                    f1.close()
                    print("this is 258 "+section)
                else :
                    lines91=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines9)
                    f1.writelines(lines91)
                    f1.close()
                    print("this is 258 "+section)               
                print("this is 258 "+str(res_main[8]))
        if stepID==261: #delay
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_261=Execute.fetchall()        
                for i in range(len(results_261)):
                    res_261=results_261[i]
                    match i:
                        case 0:
                            time = res_261[3]  
                        #print(time)                                         
                            
                stepID= res_main[1]     
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12] 
                
                f1 = open(projectPath+str(CID)+".java","a")
                lines10=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n',
                    'z.delay('+time+');\n'
                    ]
                if section == "Setup":
                    lines101=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines10)
                    f1.writelines(lines101)
                    f1.close()
                    print("this is 261 "+section)
                elif section == "Cleanup":
                    lines101=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines10)
                    f1.writelines(lines101)
                    f1.close()
                    print("this is 261 "+section)
                else :
                    lines101=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines10)
                    f1.writelines(lines101)
                    f1.close()
                    print("this is 261 "+section)                    
                print("this is 261 "+str(res_main[8])) 
        if stepID==264: #switch to frame
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            element = res_238[3]
                        case 1:
                            locator =  res_238[3]                                         
                            
                stepID= res_main[1]     
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.switchToFrame("'+locator+'", "'+element.strip()+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 264 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 235 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 264 "+section)                    
                print("this is 264 "+str(res_main[8])) 
                         
        if stepID==265: #download file from webpage
            db = pymysql.connect(host='10.170.244.3',
                database='AEMS',
                user='zbrzcatusr',
                password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_238=Execute.fetchall()        
            for i in range(len(results_238)):
                res_238=results_238[i]
                match i:
                    case 0:
                        element = res_238[3]
                    case 1:
                        expFileName =  res_238[3] 
                    case 2:
                        locator =  res_238[3]                                         
                            
                stepID= res_main[1]     
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.downloadFile("'+locator+'", "'+element.strip()+'","'+expFileName+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 265 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 265 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 265 "+section)                    
                print("this is 265 "+str(res_main[8])) 
        if stepID==266: #get text and save in variable
            
            db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_238=Execute.fetchall()        
            for i in range(len(results_238)):
                res_238=results_238[i]
                match i:
                    case 0:
                        element= res_238[3]
                        if element is not None:
                            element= res_238[3]              
                        else:
                            element=""
                    case 1:
                        locator =  res_238[3] 
                    case 2:
                        varName =  res_238[3]
                    case 3:
                        varType =  res_238[3]                            
                            
            stepID= res_main[1]     
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')  
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9] 
            section = res_main[12]        
            f1 = open(projectPath+str(CID)+".java","a")
            lines8=[
                'stepID = "'+str(stepID)+'";\n',
                'stepTypeName = "'+stepTypeName+'";\n',
                'stepName = "'+stepName+'";\n',
                'ordinalNum = ordinalNum+1;\n', 
                'section = "'+section+'";\n',                    
                'testContext.setAttribute("stepID", stepID);\n'
                'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                'testContext.setAttribute("stepName", stepName);\n',
                'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                'testContext.setAttribute("section", section);\n', 
                'z.getTextSaveInVaraible("'+locator+'", "'+element.strip()+'", "'+varName+'");\n'
                ]
            if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 266 "+section)
            elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 266 "+section)
            else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 266 "+section)                    
            print("this is 266 "+str(res_main[8]))
        if stepID==267: #switch to main                
            stepID= res_main[1]     
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')  
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9] 
            section = res_main[12]        
            f1 = open(projectPath+str(CID)+".java","a")
            lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.'+res_main[11]+';\n'
                    ]
            if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 267 "+section)
            elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 267 "+section)
            else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 267 "+section)                    
            print("this is 267 "+str(res_main[8]))
        if stepID==269: #get data and compare with variable
            db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_238=Execute.fetchall()        
            for i in range(len(results_238)):
                res_238=results_238[i]
                match i:
                    case 0:
                        element = res_238[3]
                    case 1:
                        chngValue =  res_238[3]
                    case 2:
                        locator =  res_238[3] 
                    case 3:
                        varName =  res_238[3]
                    case 4:
                        varType =  res_238[3]                                                                
                            
            stepID= res_main[1]     
            stepName = res_main[6]; 
            stepName = stepName.replace('"','\\"')  
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9] 
            section = res_main[12]        
            f1 = open(projectPath+str(CID)+".java","a")
            lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.getDataCompare("'+locator+'", "'+element.strip()+'","'+varName+'","'+varType+'","'+chngValue+'");\n'
                    ]
            if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 269 "+section)
            elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 269 "+section)
            else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 269 "+section)                    
            print("this is 269 "+str(res_main[8])) 
        if stepID==270: #scroll action
            db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_270=Execute.fetchall()        
            for i in range(len(results_270)):
                res_270=results_270[i]
                match i:
                    case 0:
                        element = res_270[3]
                    case 1:
                        locators =  res_270[3]
                        print("this is in scroll "+res_270[3]) 
            stepID= res_main[1] 
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')  
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9]
            section = res_main[12]
            f1 = open(projectPath+str(CID)+".java","a")                
            lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.scrollToElement("'+locators+'", "'+element.strip()+'");\n',
                    ]
            print("this is in scroll "+locators) 
            if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 270 "+section)
            elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 270 "+section)
            else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 270 "+section)
            print("this is 270 "+ str(res_main[8]))
                           
        if stepID==271: #double click action
            db = pymysql.connect(host='10.170.244.3',
            database='AEMS',
            user='zbrzcatusr',
            password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_238=Execute.fetchall()        
            for i in range(len(results_238)):
                res_238=results_238[i]
                match i:
                    case 0:
                        element = res_238[3]
                    case 1:
                        locator =  res_238[3]                 
            stepID= res_main[1] 
            stepName = res_main[6];     
            stepName = stepName.replace('"','\\"')  
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9]
            section = res_main[12]
            f1 = open(projectPath+str(CID)+".java","a")                
            lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.doubleClick("'+locator+'", "'+element.strip()+'");\n'
                    ]
            if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 271 "+section)
            elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 271 "+section)
            else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 271 "+section)
            print("this is 271 "+ str(res_main[8]))                 
        if stepID==272: #check element for ispresent, isnotpresent,isenabled, isnotenabled
            db = pymysql.connect(host='10.170.244.3',
                database='AEMS',
                user='zbrzcatusr',
                password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_238=Execute.fetchall()        
            for i in range(len(results_238)):
                res_238=results_238[i]
                match i:
                    case 0:
                        option = res_238[3]
                    case 1:
                        element = res_238[3]
                    case 2:
                        locator =  res_238[3]                 
            stepID= res_main[1] 
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')  
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9]
            section = res_main[12]
            f1 = open(projectPath+str(CID)+".java","a")                
            lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.checkElement("'+locator+'", "'+element.strip()+'", "'+option+'");\n'
                    ]
            if section == "Setup":
                lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                f1.writelines(lines4)
                f1.writelines(lines41)
                f1.close()
                print("this is 272 "+section)
            elif section == "Cleanup":
                lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                f1.writelines(lines4)
                f1.writelines(lines41)
                f1.close()
                print("this is 272 "+section)
            else :
                lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                f1.writelines(lines4)
                f1.writelines(lines41)
                f1.close()
                print("this is 272 "+section)
            print("this is 272 "+ str(res_main[8])) 
        
        if stepID==230: #refresh the page               
            stepID= res_main[1]     
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')              
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9] 
            section = res_main[12]        
            f1 = open(projectPath+str(CID)+".java","a")
            lines8=[
                'stepID = "'+str(stepID)+'";\n',
                'stepTypeName = "'+stepTypeName+'";\n',
                'stepName = "'+stepName+'";\n',
                'ordinalNum = ordinalNum+1;\n', 
                'section = "'+section+'";\n',                    
                'testContext.setAttribute("stepID", stepID);\n'
                'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                'testContext.setAttribute("stepName", stepName);\n',
                'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                'testContext.setAttribute("section", section);\n', 
                'z.refresh();\n',
                ]
            if section == "Setup":
                lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                f1.writelines(lines8)
                f1.writelines(lines81)
                f1.close()
                print("this is 230 "+section)
            elif section == "Cleanup":
                lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                         'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                f1.writelines(lines8)
                f1.writelines(lines81)
                f1.close()
                print("this is 230 "+section)
            else :
                lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                f1.writelines(lines8)
                f1.writelines(lines81)
                f1.close()
                print("this is 230 "+section)                    
            print("this is 230 "+str(res_main[8]))                           
                           
        if stepID==273: #clear browser cache                 
            db = pymysql.connect(host='10.170.244.3',
                 database='AEMS',
                 user='zbrzcatusr',
                 password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_235=Execute.fetchall()        
            for i in range(len(results_235)):
                res_235=results_235[i]
                browser = res_235[3] 
            stepID= res_main[1] 
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')              
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9]
            section = res_main[12]
            f1 = open(projectPath+str(CID)+".java","a")                
            lines1=[
                'stepID = "'+str(stepID)+'";\n',
                'stepTypeName = "'+stepTypeName+'";\n',
                'stepName = "'+stepName+'";\n',
                'ordinalNum = ordinalNum+1;\n',
                'section = "'+section+'";\n',                                         
                'testContext.setAttribute("stepID", stepID);\n'
                'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                'testContext.setAttribute("stepName", stepName);\n',
                'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                'testContext.setAttribute("section", section);\n', 
                'z.clearCache("'+browser+'");\n',
                ]
            if section=="Setup":
                lines11=['api_call.startSetUp(execID, stepID,stepName);\n',
                'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                f1.writelines(lines1)
                f1.writelines(lines11)
                f1.close()
                print("this is 273 "+section)
            elif section == "Cleanup":
                lines11= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                          'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                f1.writelines(lines1)
                f1.writelines(lines11)
                f1.close()
                print("this is 273 "+section)
            else :
                lines11=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                f1.writelines(lines1)
                f1.writelines(lines11)
                f1.close()
                print("this is 273 "+section)
            print("this is 273 "+str(res_main[8]))  
        if stepID==274: #Keyboard actions
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_239=Execute.fetchall()        
                for i in range(len(results_239)):
                    res_239=results_239[i]
                    match i:
                        case 0:
                            element = res_239[3]
                        case 1:
                            action =  res_239[3]
                        case 2:
                            locator =  res_239[3] 
                        case 3:
                            times = res_239[3]                          
                            if times=="":                         
                                times = "0"                             
                            else:
                                times = res_239[3]                                                         
                        case 4:
                            text =  res_239[3]
                            if text is not None:
                                text = res_239[3]
                            else:
                                text=""            
              
                stepID= res_main[1]     
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')                  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]          
                f1 = open(projectPath+str(CID)+".java","a")                                    
                lines5=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.keyboardActions("'+locator+'", "'+element.strip()+'", "'+action+'","'+text+'",'+times+');\n'
                    ]
                if section == "Setup":
                    lines51=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0, section);\n']
                    f1.writelines(lines5)
                    f1.writelines(lines51)
                    f1.close()
                    print("this is 274 "+section)
                elif section == "Cleanup":                    
                    lines51=['api_call.startCleanUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0, section);\n']
                    f1.writelines(lines5)
                    f1.writelines(lines51)
                    f1.close()
                    print("this is 274 "+section)
                else :
                    lines51=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0, section);\n']
                    f1.writelines(lines5)
                    f1.writelines(lines51)
                    f1.close()
                    print("this is 274 "+section)
                print("this is 274 "+str(res_main[8])) 
        
        if stepID==275: #set attribute 
            db = pymysql.connect(host='10.170.244.3',
                database='AEMS',
                user='zbrzcatusr',
                password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_242=Execute.fetchall()        
            for i in range(len(results_242)):
                res_242=results_242[i]
                match i:
                    case 0:
                        element = res_242[3]                            
                    case 1:
                        attrName = res_242[3]                           
                    case 2:
                        locator =  res_242[3]
                    case 3:
                        option =  res_242[3]
                        if option is not None:
                            option = res_242[3]
                        else:
                            option="" 
                    case 4:
                        value =  res_242[3]                                                       
            stepID= res_main[1]     
            stepName = res_main[6]; 
            stepName = stepName.replace('"','\\"')              
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9] 
            section = res_main[12]        
            f1 = open(projectPath+str(CID)+".java","a")
            lines8=['stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.setAttribute("'+locator+'", "'+element.strip()+'", "'+attrName+'","'+value+'","'+option+'");\n',
                    ]
            if section == "Setup":
                lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                f1.writelines(lines8)
                f1.writelines(lines81)
                f1.close()
                print("this is 275 "+section)
            elif section == "Cleanup":
                lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                         'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                f1.writelines(lines8)
                f1.writelines(lines81)
                f1.close()
                print("this is 275 "+section)
            else :
                lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                f1.writelines(lines8)
                f1.writelines(lines81)
                f1.close()
                print("this is 275 "+section)              
            print("this is 275 "+str(res_main[8])) 
                
        if stepID==276: #get attribute and compare
            db = pymysql.connect(host='10.170.244.3',
            database='AEMS',
            user='zbrzcatusr',
            password='ZbrZc@TU$r03')
            Execute=db.cursor()
            Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
            results_242=Execute.fetchall()        
            for i in range(len(results_242)):
                res_242=results_242[i]
                match i:
                    case 0:
                        element = res_242[3]                            
                    case 1:
                        expected = res_242[3]                           
                    case 2:
                        attrName =  res_242[3]
                    case 3:
                       locator =  res_242[3]
                                                       
            stepID= res_main[1]     
            stepName = res_main[6];
            stepName = stepName.replace('"','\\"')              
            stepTypeName = res_main[10];            
            #ordinalNum= res_main[9] 
            section = res_main[12]        
            f1 = open(projectPath+str(CID)+".java","a")
            lines8=[
                'stepID = "'+str(stepID)+'";\n',
                'stepTypeName = "'+stepTypeName+'";\n',
                'stepName = "'+stepName+'";\n',
                'ordinalNum = ordinalNum+1;\n',
                'section = "'+section+'";\n',                     
                'testContext.setAttribute("stepID", stepID);\n'
                'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                'testContext.setAttribute("stepName", stepName);\n',
                'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                'testContext.setAttribute("section", section);\n', 
                'z.getAttributeAndCompare("'+locator+'", "'+element.strip()+'", "'+attrName+'","'+expected.strip()+'");\n',
                ]
            if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 276 "+section)
            elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 276 "+section)
            else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 276 "+section)              
            print("this is 276 "+str(res_main[8])) 
        
        if stepID==277: #clear text from element
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            element = res_238[3]
                        case 1:
                            locator =  res_238[3]                 
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.clearText("'+locator+'", "'+element.strip()+'");\n'
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 277 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 277 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 277 "+section)
                print("this is 277 "+ str(res_main[8]))
        if stepID == 278: #navigate back to previous page
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")             
                lines3=[                                
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n', 
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.navigateBack();\n',
                    ]
                if section == "Setup":
                    lines31=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                                                
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 278 "+section)
                elif section == "Cleanup":                
                    lines31=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                                                
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 278 "+section)
                else :
                    lines31=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                                                
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 278 "+section)                                                              
                print("this is 278 "+str(res_main[8])) 
        if stepID == 279: #switch browser
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_235=Execute.fetchall()        
                for i in range(len(results_235)):
                    res_235=results_235[i]
                    browser = res_235[3]
                    if browser == "chrome":
                        browserLine="z=zc;"
                    elif browser =="Firefox":
                        browserLine="z=zf;"
                    elif browser =="Edge":
                        browserLine="z=ze;" 
                    elif browser =="Safari":
                        browserLine="z=zs;"
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")             
                lines3=[                                
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n', 
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    browserLine+'\n',
                    ]
                if section == "Setup":
                    lines31=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 279 "+section)
                elif section == "Cleanup":                
                    lines31=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 279 "+section)
                else :
                    lines31=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 279 "+section)                                                              
                print("this is 279 "+str(res_main[8])) 
        if stepID == 280: #get substring from variable and compare
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            element = res_242[3]
                            print("this is the element "+element) 
                        case 1:
                            expected = res_242[3]                             
                            print("this is the expected "+expected) 
                        case 2:
                            intValue =  res_242[3]
                            if intValue is not None:
                                intValue = res_242[3]
                            else:
                                intValue=""   
                        case 3:
                            locator =  res_242[3]
                        case 4:
                            elementCheck = res_242[3]
                            if elementCheck is not None:
                                elementCheck = res_242[3]
                            else:
                                elementCheck=""   
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.getSubstringAndCompare("'+locator+'", "'+element.strip()+'", "'+expected+'", "'+intValue+'", "'+elementCheck+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 280 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 280 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 280 "+section)              
                print("this is 280 "+str(res_main[8]))
        if stepID == 281: #Give name to a label before designing
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            labelName = res_242[3]    
                                                                          
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.renameLabel("'+labelName.strip()+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 281 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 281 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 281 "+section)              
                print("this is 281 "+str(res_main[8]))  
        if stepID == 282: #Delete label 
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            deleteLabel = res_242[3]    
                                                                          
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.deleteLabel("'+deleteLabel+'");\n',
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 282 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 281 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 282 "+section)              
                print("this is 282 "+str(res_main[8]))
        if stepID == 283: #minimize and maximize the webpage window
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_235=Execute.fetchall()        
                for i in range(len(results_235)):
                    res_235=results_235[i]
                    maxmin = res_235[3]                    
                stepID= res_main[1] 
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')                       
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines1=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.windowMaxMin("'+maxmin+'");\n'
                    ]                           
                if section=="Setup":
                    lines11=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 283 "+section)
                elif section == "Cleanup":
                    lines11= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 283 "+section)
                else :
                    lines11=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 283 "+section)
                print("this is 283 "+str(res_main[8])) 
        if stepID == 284: #drop objects on canvas
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_284=Execute.fetchall() 
                for i in range(len(results_284)):
                    res_284=results_284[i]
                    match i:
                        case 0:
                            element = res_284[3]
                            print("this is the element "+element) 
                        case 1:
                            locator =  res_284[3]                             
                            print("this is the expected "+locator) 
                        case 3:
                            xpos =  res_284[3]
                            if xpos is not None:
                                xpos = res_284[3]
                            else:
                                xpos=""   
                        case 4:
                            ypos = res_284[3]
                            if ypos is not None:
                                ypos = res_284[3]
                            else:
                                ypos="" 
                        case 2:
                            varName = res_284[3]
                            if varName is not None:
                                varName = res_284[3]
                            else:
                                varName=""
                                                       
                stepID= res_main[1] 
                stepName = res_main[6];
                stepName = stepName.replace('"','\\"')                       
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines1=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.dropOnCanvas("'+locator+'", "'+element.strip()+'", '+xpos+', '+ypos+',"'+varName+'");\n'
                    ]                           
                if section=="Setup":
                    lines11=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 284 "+section)
                elif section == "Cleanup":
                    lines11= ['api_call.startCleanUp(execID, stepID,stepName);\n',
                              'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 284 "+section)
                else :
                    lines11=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines1)
                    f1.writelines(lines11)
                    f1.close()
                    print("this is 284 "+section)
                print("this is 284 "+str(res_main[8]))
        if stepID == 285: #Zoom label 
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            zoomLabel = res_242[3]    
                                                                          
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.zoom("'+zoomLabel+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 285 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 285 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 285 "+section)              
                print("this is 285 "+str(res_main[8]))
        if stepID == 286: #rotate label 
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            rotateLabel = res_242[3]    
                                                                          
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.rotate("'+rotateLabel+'");\n',
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 286 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 286 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 286 "+section)              
                print("this is 286 "+str(res_main[8]))
        if stepID == 287: #Dialogue window
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            element = res_238[3]
                        case 1:
                            locator =  res_238[3]                 
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.dialogueWindow("'+locator+'", "'+element.strip()+'");\n'
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 287 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 287 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 287 "+section)
                print("this is 287 "+ str(res_main[8]))                                                                       
        if stepID == 268: #Upload file
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i: 
                        case 0: 
                            locator = res_238[3]                           
                        case 1:
                            filePath = "C:/selenium/moneybadgerWebportal/uploadFile/"    
                            filePath = os.path.join(filePath, str(CID))             
                            try: 
                                os.makedirs(filePath, exist_ok = True) 
                                print("Directory '%s' created successfully" % CID) 
                            except OSError as error: 
                                print("Directory '%s' can not be created" % CID)
                            fileName= res_238[3]            
                            parameterValue = res_238[6]                             
                            f2=open(filePath+'/'+fileName, 'wb')  
                            db = pymysql.connect(host='10.170.244.3',
                                                         database='AEMS',
                                                         user='zbrzcatusr',
                                                         password='ZbrZc@TU$r03')
                            Execute=db.cursor()                              
                            Execute.callproc("Selenium_Tests.TED_Read_FileData",[parameterValue])
                            results1=Execute.fetchall()           
                            for i in range(len(results1)):
                                res1=results1[i]                         
                                f2.write(res1[0])                               
                        case 2:
                            element = res_238[3] 
                f2.close()
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.uploadFile("'+element+'", "'+ locator.strip()+'", "'+fileName+'", "'+str(CID)+'");\n',
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 268 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 268 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 268 "+section)
                print("this is 268 "+ str(res_main[8]))
        if stepID == 292: #authentication code for apple and google 
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            option = res_242[3]                                                                           
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.socialAccAuthn("'+option+'");\n',
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 292 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 292 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 292 "+section)              
                print("this is 292 "+str(res_main[8]))
        if stepID == 293: #get CSSValue and compare
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            element = res_242[3]                            
                        case 1:
                            expected = res_242[3]                           
                        case 2:
                            attrName =  res_242[3]
                        case 3:
                            locator =  res_242[3]
                                                       
                stepID= res_main[1]     
                stepName = res_main[6];   
                stepName = stepName.replace('"','\\"')                    
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.getCSSAndCompare("'+locator+'", "'+element.strip()+'", "'+attrName+'","'+expected.strip()+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 293 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 293 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 293 "+section)              
                print("this is 293 "+str(res_main[8])) 
        if stepID == 294: #click action on LDA object property menu
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            element = res_238[3]
                        case 1:
                            locator =  res_238[3]                 
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.objectClick("'+locator+'", "'+element.strip()+'");\n'
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 294 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 294 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 294 "+section)
                print("this is 294 "+ str(res_main[8]))
        if stepID == 295: #Generate random email and save in variable
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:                      
                        case 0:
                            varName =  res_238[3]    
                stepID= res_main[1]     
                stepName = res_main[6];     
                stepName = stepName.replace('"','\\"')                  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.randomEmail("'+varName+'");\n'
                   ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 295 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 295 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 295 "+section)                    
                print("this is 295 "+str(res_main[8]))
        if stepID == 296: #Get verification code
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:                      
                        case 0:
                            varName =  res_238[3]    
                stepID= res_main[1]     
                stepName = res_main[6];     
                stepName = stepName.replace('"','\\"')                  
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.verificationCode("'+varName+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 296 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 296 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 296 "+section)                    
                print("this is 296 "+str(res_main[8])) 
        if stepID == 297: #Edit elements/objects on canvas
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            objName = res_242[3]                                                                          
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.editCanvasElement("'+objName+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 297 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 297 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 297 "+section)              
                print("this is 297 "+str(res_main[8])) 
        
        if stepID == 298: #set and get compare from printer
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            value= res_238[3]
                        case 1:
                            sgd =  res_238[3]                 
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.setAndGetCompare("'+sgd+'", "'+value+'");\n'
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 298 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 298 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 298 "+section)
                print("this is 298 "+ str(res_main[8]))  
        if stepID == 299: #get And Compar From Printer And Webpage
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            element = res_242[3]                           
                        case 1:
                            locator = res_242[3]                                                     
                        case 2:
                            sgd =  res_242[3]
                                                       
                stepID= res_main[1]     
                stepName = res_main[6];    
                stepName = stepName.replace('"','\\"')                   
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n',
                    'section = "'+section+'";\n',                     
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.getAndCompareFromPrinterAndWebpage("'+locator+'", "'+element.strip()+'", "'+sgd+'");\n',
                    ]                
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 299 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 299 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 299 "+section)              
                print("this is 299 "+str(res_main[8]))
        if stepID == 300: #Do command for printer
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i:
                        case 0:
                            value= res_238[3]
                        case 1:
                            sgd =  res_238[3]                 
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.Do("'+sgd+'", "'+value+'");\n'
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 300 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 300 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 300 "+section)
                print("this is 300 "+ str(res_main[8])) 
        if stepID == 301: #send File To Printer
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_238=Execute.fetchall()        
                for i in range(len(results_238)):
                    res_238=results_238[i]
                    match i: 
                        case 0: 
                            locator = res_238[3]                           
                        case 1:
                            filePath = "C:/Users/AS3482/eclipse-workspace/moved_PC/moneybadgerWebportal/uploadFile/"    
                            filePath = os.path.join(filePath, CID)             
                            try: 
                                os.makedirs(filePath, exist_ok = True) 
                                print("Directory '%s' created successfully" % CID) 
                            except OSError as error: 
                                print("Directory '%s' can not be created" % CID)
                            fileName= "sample"#(res_238[3])            
                            parameterValue = res_238[6]                             
                            f3=open(filePath+'/'+fileName, 'wb')  
                            db = pymysql.connect(host='10.170.244.3',
                                                         database='AEMS',
                                                         user='zbrzcatusr',
                                                         password='ZbrZc@TU$r03')
                            Execute=db.cursor()                              
                            Execute.callproc("Selenium_Tests.TED_Read_FileData",[parameterValue])
                            results1=Execute.fetchall()           
                            for i in range(len(results1)):
                                res1=results1[i]                         
                                f3.write(res1[0])
                                f3.close()                                
                        case 2:
                            element = res_238[3]
                                                          
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")                
                lines4=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                         
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.sendFileToPrinter("'+element+'", "'+ locator.strip()+'", "'+fileName+'", "'+CID+'");\n'
                    ]
                if section == "Setup":
                    lines41=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 301 "+section)
                elif section == "Cleanup":
                    lines41=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 301 "+section)
                else :
                    lines41=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']
                    f1.writelines(lines4)
                    f1.writelines(lines41)
                    f1.close()
                    print("this is 301 "+section)
                print("this is 301 "+ str(res_main[8]))
        if stepID == 303: #switch to main parent window 
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_242=Execute.fetchall()        
                for i in range(len(results_242)):
                    res_242=results_242[i]
                    match i:
                        case 0:
                            varName = res_242[3]               
                stepID= res_main[1]     
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9] 
                section = res_main[12]        
                f1 = open(projectPath+str(CID)+".java","a")             
                lines8=[
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n',                    
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n', 
                    'z.'+res_main[11]+'("'+varName+'");\n'
                    ]
                if section == "Setup":
                    lines81=['api_call.startSetUp(execID, stepID,stepName);\n',
                    'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 303 "+section)
                elif section == "Cleanup":
                    lines81=['api_call.startCleanUp(execID, stepID,stepName);\n',
                             'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 303 "+section)
                else :
                    lines81=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                         
                    f1.writelines(lines8)
                    f1.writelines(lines81)
                    f1.close()
                    print("this is 303 "+section)                    
                print("this is 303 "+str(res_main[8]))
        if stepID == 304: #start loop
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_235=Execute.fetchall()        
                for i in range(len(results_235)):
                    res_235=results_235[i]
                    loop = res_235[3]                                       
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")             
                lines3=[                                
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n', 
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n'                    
                    ]
                if section == "Setup":
                    lines31=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n',
                        'for(i=0;i<'+loop+';i++){\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 304 "+section)
                elif section == "Cleanup":                
                    lines31=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n',
                        'for(int i=0;i<'+loop+';i++){\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 304 "+section)
                else :
                    lines31=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n',
                             'for(int i=0;i<'+loop+';i++){\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 304 "+section)                                                              
                print("this is 304 "+str(res_main[8])) 
        if stepID == 305: #End loop
                db = pymysql.connect(host='10.170.244.3',
                    database='AEMS',
                    user='zbrzcatusr',
                    password='ZbrZc@TU$r03')
                Execute=db.cursor()
                Execute.callproc("Selenium_Tests.StepObj_GetParameters",[stepData])
                results_235=Execute.fetchall()        
                for i in range(len(results_235)):
                    res_235=results_235[i]
                    loop = res_235[3]                                       
                stepID= res_main[1] 
                stepName = res_main[6]; 
                stepName = stepName.replace('"','\\"')                      
                stepTypeName = res_main[10];            
                #ordinalNum= res_main[9]
                section = res_main[12]
                f1 = open(projectPath+str(CID)+".java","a")             
                lines3=[ 
                    '}\n',                                                   
                    'stepID = "'+str(stepID)+'";\n',
                    'stepTypeName = "'+stepTypeName+'";\n',
                    'stepName = "'+stepName+'";\n',
                    'ordinalNum = ordinalNum+1;\n', 
                    'section = "'+section+'";\n', 
                    'testContext.setAttribute("stepID", stepID);\n'
                    'testContext.setAttribute("stepTypeName", stepTypeName);\n',
                    'testContext.setAttribute("stepName", stepName);\n',
                    'testContext.setAttribute("ordinalNum", ordinalNum);\n',
                    'testContext.setAttribute("section", section);\n' 
                    ]
                if section == "Setup":
                    lines31=['api_call.startSetUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 305 "+section)
                elif section == "Cleanup":                
                    lines31=['api_call.startCleanUp(execID, stepID,stepName);\n',
                        'api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 305 "+section)
                else :
                    lines31=['api_call.insertStep(execID, ordinalNum, stepID, stepTypeName, stepName, "Pass", 0,section);\n']                        
                    f1.writelines(lines3)
                    f1.writelines(lines31)
                    f1.close()
                    print("this is 305 "+section)                                                              
                print("this is 305 "+str(res_main[8]))

                                   
                         
              
                           
    
           #caseID("4000013",705,397)
#caseID("4000014","916","652")
#python -c "import generic_moneybadger; print(generic_moneybadger.caseID("4000069","916","328","652"))"
 

    
    
    

    