pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        STACK_NAME = "cloud_native_microservices"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/aayush0982/Cloud_Native_App.git'
            }
        }

        stage('Build Services (Maven)') {
            steps {
                sh '''
                mvn clean package -DskipTests -f Eureka/pom.xml
                mvn clean package -DskipTests -f SrpingCloudSerevr/pom.xml
                mvn clean package -DskipTests -f APIGateway/pom.xml

                mvn clean package -DskipTests -f Product/pom.xml
                mvn clean package -DskipTests -f ProductCatalogue/pom.xml
                mvn clean package -DskipTests -f Inventory/pom.xml
                mvn clean package -DskipTests -f Pricing/pom.xml

                mvn clean package -DskipTests -f Cart/pom.xml
                mvn clean package -DskipTests -f Recommendation/pom.xml
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                docker build -t eureka-service ./Eureka
                docker build -t config-service ./SrpingCloudSerevr
                docker build -t gateway-service ./APIGateway

                docker build -t product-service ./Product
                docker build -t catalog-service ./ProductCatalogue
                docker build -t inventory-service ./Inventory
                docker build -t pricing-service ./Pricing

                docker build -t cart-service ./Cart
                docker build -t recommendation-service ./Recommendation
                '''
            }
        }

        stage('Init Docker Swarm (if needed)') {
            steps {
                sh '''
                docker info | grep -q "Swarm: active" || docker swarm init
                '''
            }
        }

        stage('Remove Old Stack') {
            steps {
                sh '''
                echo "Removing old stack..."
                docker stack rm $STACK_NAME || true
                sleep 15
                '''
            }
        }

        stage('Deploy Stack') {
            steps {
                sh '''
                echo "Deploying new stack..."
                docker stack deploy -c docker-stack.yml $STACK_NAME
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                echo "Services:"
                docker stack services $STACK_NAME

                echo "Tasks:"
                docker stack ps $STACK_NAME
                '''
            }
        }

    }

    post {
        success {
            echo "🚀 All microservices deployed successfully via Docker Swarm!"
        }
        failure {
            echo "❌ Pipeline failed — check logs carefully."
        }
    }
}
