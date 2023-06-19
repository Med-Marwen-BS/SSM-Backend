node {
   def commit_id
   stage('Preparation') {
     checkout scm
     bat "git rev-parse --short HEAD > .git/commit-id"
     commit_id = readFile('.git/commit-id').trim()
   }


   stage('Docker Build/Push Of Eureka Server') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('service-registry') {
           bat 'docker build -t marwen95/ssm-service-registry:latest .'
           bat 'docker login -u marwen95 -p maratanna1920'
           bat 'docker push marwen95/ssm-service-registry:latest'
       }



      }

   }

    stage('Docker Build/Push Of Config Server') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('config-server') {
           bat 'docker build -t marwen95/ssm-config-server:latest .'
           bat 'docker login -u marwen95 -p maratanna1920'
           bat 'docker push marwen95/ssm-config-server:latest'
       }
      }

   }

   stage('Docker Build/Push Of Gateway Service') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('api-gateway') {
           bat 'docker build -t marwen95/ssm-api-gateway:latest .'
           bat 'docker login -u marwen95 -p maratanna1920'
           bat 'docker push marwen95/ssm-api-gateway:latest'
       }
      }

   }

   stage('Docker Build/Push Of Mail Service') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('Mail-App') {
           bat 'docker build -t marwen95/ssm-mail-service:latest .'
           bat 'docker login -u marwen95 -p maratanna1920'
           bat 'docker push marwen95/ssm-mail-service:latest'
       }
      }

   }

   stage('Docker Build/Push Of Team Service') {
   withCredentials([usernamePassword(credentialsId: '829fc465-47c0-4903-a6b0-436f2ad50cdb', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       dir('team-service') {
           bat 'docker build -t marwen95/ssm-team-service:latest .'
           bat 'docker login -u marwen95 -p maratanna1920'
           bat 'docker push marwen95/ssm-team-service:latest'
       }
      }

   }




}