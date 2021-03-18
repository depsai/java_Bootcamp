node {

  try {

      

    stage('github checkout'){

        git credentialsId: 'github', url: 'https://github.com/KalaivananArjunan/Devops_bootcamp.git'

    }

    stage('clean up'){

       def mavenHome = tool name: 'maven3', type: 'maven'

       def mavenCMD = "${mavenHome}/bin/mvn"

       sh "${mavenCMD} clean"

    }

    stage('build, test and package'){

       def mavenHome = tool name: 'maven3', type: 'maven'

       def mavenCMD = "${mavenHome}/bin/mvn"

       sh "${mavenCMD} package"

    }
    
    stage('SonarQube code analysis'){

        withSonarQubeEnv(){

           def mavenHome = tool name: 'maven3', type: 'maven'

           def mavenCMD = "${mavenHome}/bin/mvn"

           sh "${mavenCMD} sonar:sonar"

        }

    }
    stage('Docker Image Build'){

       sh"docker build -t kalaivananarjunan/devopsbootcamp2020 ."
       sh 'pwd'

    }

    stage('Docker image push') {

        
        sh "cat my_password.txt | docker login --username kalaivananarjunan --password-stdin"
        
        sh 'docker push kalaivananarjunan/devopsbootcamp2020:latest'
    }
    
    stage('Ansible Playbook - EC2 Instance Creation')
    {
        ansiblePlaybook credentialsId: 'devops-key', playbook: '/var/lib/jenkins/workspace/Devops_bootcamp_casestudy/AWS_instance_creation.yml'
        //ansiblePlaybook credentialsId: 'ansible-admin', playbook: '/var/lib/jenkins/workspace/Devops_bootcamp_casestudy/AWS_instance_creation.yml'
    }
    
    stage('Fetching IP'){
        
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws accesskey', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])
        {

                    sleep 60

                    def ipfetch ='aws ec2 describe-instances --filters Name=instance-type,Values=t2.micro --region us-east-2 --query "Reservations[*].Instances[*][PublicIpAddress]"'

                    def out = sh script :"${ipfetch}" , returnStdout:true

                    def ipsplit = out.split('"')
                 
                    ipAddress = ipsplit[1]

                    print ipAddress
                    
                    //print out

                }
    }
	
	stage('Installing Docker on EC2'){
        
         withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws accesskey', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])
        {
        
        def dockerCMD = "sudo yum install docker -y"
        

        sshagent(['devops-key']) {
            sh "ssh -o StrictHostKeyChecking=no ec2-user@${ipAddress} ${dockerCMD}"
          
        }
        }

    }
	
	stage('Stating Docker Service'){
        
         withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws accesskey', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])
        {
        def dockerCMD = "sudo service docker start"
        
        sshagent(['devops-key']) {
            
          sh "ssh -o StrictHostKeyChecking=no ec2-user@${ipAddress} ${dockerCMD}"

        }
        }

    }
	
	stage('Executing Docker Image'){
        
         withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws accesskey', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']])
        {
        def dockerCMD = "sudo docker run -d -p 80:8885 kalaivananarjunan/devopsbootcamp2020:latest"
        
        sshagent(['devops-key']) {
            
          sh "ssh -o StrictHostKeyChecking=no ec2-user@${ipAddress} ${dockerCMD}"

        }
        }

    }
    
  }
 catch (e) {

     currentBuild.result = "FAILED"

     triggerEmail()

     throw e

  }
 }
 
 def triggerEmail() {

  emailext ( 

      body: 'Your Build has been failed. Please look into it.', 

      to : 'hydra060490@gmail.com', 

      subject: 'Build-Failed'

  )

}
