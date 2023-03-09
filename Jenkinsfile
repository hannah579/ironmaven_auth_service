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
        sh "docker run -d --rm --name authapi -p 8081:8081 authapi:v1.0"
     }
    
    stage('User Acceptance Test - AuthService') {
	
	  def response= input message: 'Is this build good to go?',
	   parameters: [choice(choices: 'Yes\nNo', 
	   description: '', name: 'Pass')]
	
	  if(response=="Yes") {
	  
	    stage('Create and Expose Kubernetes Deployment - AuthApi') {
	      sh "docker stop authapi"
	      sh "kubectl create deployment auth --image=authapi:v1.0"
	      sh "set env deployment/auth API_HOST=\$(kubectl get service/data -o jsonpath='{.spec.clusterIP}'):8080"
	      sh "kubectl expose deployment auth --type=LoadBalancer --port=8081"
	      sh "kubectl describe deployment/auth"
	    }
	   }
    }
    
    stage("View Production Deployment"){
    	sh "kubectl get all"
    }
}
