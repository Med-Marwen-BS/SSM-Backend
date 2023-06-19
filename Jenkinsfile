node {
   def commit_id
   stage('Preparation') {
     checkout scm
     sh "git rev-parse --short HEAD > .git/commit-id"
     commit_id = readFile('.git/commit-id').trim()
   }


   stage('Docker Build/Push Of Eureka Server') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('service-registry') {
           sh 'docker build -t marwen95/ssm-service-registry:latest .'
           sh 'docker login -u $USERNAME -p $PASSWORD'
           sh 'docker push marwen95/ssm-service-registry:latest'
       }



      }

   }

    stage('Docker Build/Push Of Config Server') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('config-server') {
           sh 'docker build -t marwen95/ssm-config-server:latest .'
           sh 'docker login -u $USERNAME -p $PASSWORD'
           sh 'docker push marwen95/ssm-config-server:latest'
       }
      }

   }

   stage('Docker Build/Push Of Gateway Service') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('api-gateway') {
           sh 'docker build -t marwen95/ssm-api-gateway:latest .'
           sh 'docker login -u $USERNAME -p $PASSWORD'
           sh 'docker push marwen95/ssm-api-gateway:latest'
       }
      }

   }

   stage('Docker Build/Push Of Mail Service') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('Mail-App') {
           sh 'docker build -t marwen95/ssm-mail-service:latest .'
           sh 'docker login -u $USERNAME -p $PASSWORD'
           sh 'docker push marwen95/ssm-mail-service:latest'
       }
      }

   }

   stage('Docker Build/Push Of Team Service') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('team-service') {
           sh 'docker build -t marwen95/ssm-team-service:latest .'
           sh 'docker login -u $USERNAME -p $PASSWORD'
           sh 'docker push marwen95/ssm-team-service:latest'
       }
      }

   }




}