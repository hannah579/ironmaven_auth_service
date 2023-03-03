node {
    stage ("Checkout AuthService"){
        git branch: 'main', url: 'https://github.com/hannah579/ironmaven_auth_service.git'
    }
    
    stage ("Gradle Build - AuthService") {
        sh 'gradle clean build'
    }
    
    stage ("Gradle Bootjar-Package - AuthService") {
        sh 'gradle bootjar'
    }
	
	stage ("Containerize the app-docker build - AuthApi") {
        sh 'docker build --rm -t authapi:v1.0 .'
    }
    
    stage ("Inspect the docker image - AuthApi"){
        sh "docker images authapi:v1.0"
        sh "docker inspect authapi:v1.0"
    }
    
    stage ("Run Docker container instance - AuthApi"){
        sh "docker run -d --name auth -p 8081:8081 authapi:v1.0"
     }
    
    stage('User Acceptance Test - AuthService') {
	
	  def response= input message: 'Is this build good to go?',
	   parameters: [choice(choices: 'Yes\nNo', 
	   description: '', name: 'Pass')]
	
	  if(response=="Yes") {
	  
	    stage('Release - AuthService') {
	      sh 'docker stop auth'
	      sh 'echo MCC AuthService is ready to release!'
	    }
	  }
    }
}
