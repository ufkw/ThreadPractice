#include <stdlib.h>

int main(void) 
{
    char * command = malloc(10*sizeof(char));
    char * arg1 = malloc(500*sizeof(char));
    char * arg2 = malloc(500*sizeof(char));

    memset(command, '\0', sizeof(command));
    memset(arg1, '\0', sizeof(arg1));
    memset(arg2, '\0', sizeof(arg2));

    char * input = malloc(sizeof(command) + sizeof(arg1) + sizeof(arg2));
    memset(input, '\0', sizeof(input));
    while(1)
    {
        //get input
        gets(input);
        
        // run infinitely

        //tokenize input into command, arg1, arg2
        token = strtok(input, " ");
        

        int count = 0;

        while(token != NULL)
        {
            if(count == 0)
            {
                command = strcpy(token);
            }
            else if(count == 1)
            {
                arg1 = strcpy(token);
            }
            else if(count == 2)
            {
                arg2 = strcpy(token);
            }


            token = strtok(NULL, " ");
            count++;
        }
        

        if(input[0] != '\0')
        {
            //if input exists
            pid_t pid = fork();    //create child thread

            if(pid == 0)
            {
                //child process
                //new thread converts and executes command
                if(command[0] == 'c' && command[1] == 'd')          //cd to cd
                {
                    if(arg1[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg1);
                    }
                    
                    if(arg2[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg2);
                    }

                    system(command);
                    //need error catch?
                }
                else if(strcmp(command, "dir") == 0)
                {
                    memset(command, '\0', sizeof(command));
                    command = "ls";

                    if(arg1[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg1);
                    }
                    
                    if(arg2[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg2);
                    }

                    system(command);
                }
                else if(strcmp(command, "type") == 0)
                {
                    memset(command, '\0', sizeof(command));
                    command = "cat";

                    if(arg1[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg1);
                    }
                    
                    if(arg2[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg2);
                    }

                    system(command);
                }
                else if(strcmp(command, "del") == 0)
                {
                    memset(command, '\0', sizeof(command));
                    command = "rm";

                    if(arg1[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg1);
                    }
                    
                    if(arg2[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg2);
                    }

                    system(command);
                }
                else if(strcmp(command, "ren") == 0)
                {
                    memset(command, '\0', sizeof(command));
                    command = "mv";

                    if(arg1[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg1);
                    }
                    
                    if(arg2[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg2);
                    }

                    system(command);
                }
                else if(strcmp(command, "copy") == 0)
                {
                    memset(command, '\0', sizeof(command));
                    command = "cp";   

                    if(arg1[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg1);
                    }
                    
                    if(arg2[0] != '\0')
                    {
                        strcat(command, " ");
                        strcat(command, arg2);
                    }

                    system(command);         
                }
                else if(strcmp(command, 0x03) == 0)
                {
                    //ToWrite ctrl-c to exit
                    System.out.println("ctrl-c");
                    exit();
                }
                else
                {
                    //command not recognized
                    System.out.println(command + " is not recognized as an internal or external command, operable program or batch file.");
                    
                }

            }
        }

        

    }

    return(0);
}


// Class DOS2UNIX extends Thread
// {

// }