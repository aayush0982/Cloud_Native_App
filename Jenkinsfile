pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        STACK_NAME = "mystack"
        DOCKER_USER = "saxenaaayush9000"
    }

    stages {

        // -----------------------------
        // CHECKOUT CODE
        // -----------------------------
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/aayush0982/Cloud_Native_App.git'
            }
        }

        // -----------------------------
        // BUILD SERVICES (MAVEN)
        // -----------------------------
        stage('Build Services') {
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

        // -----------------------------
        // BUILD DOCKER IMAGES
        // -----------------------------
        stage('Docker Build') {
            steps {
                sh '''
                docker build -t $DOCKER_USER/eureka-service ./Eureka
                docker build -t $DOCKER_USER/config-service ./SrpingCloudSerevr
                docker build -t $DOCKER_USER/gateway-service ./APIGateway

                docker build -t $DOCKER_USER/product-service ./Product
                docker build -t $DOCKER_USER/catalog-service ./ProductCatalogue
                docker build -t $DOCKER_USER/inventory-service ./Inventory
                docker build -t $DOCKER_USER/pricing-service ./Pricing

                docker build -t $DOCKER_USER/cart-service ./Cart
                docker build -t $DOCKER_USER/recommendation-service ./Recommendation
                '''
            }
        }

        // -----------------------------
        // DOCKER LOGIN
        // -----------------------------
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-cred',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh '''
                    echo "$PASS" | docker login -u "$USER" --password-stdin
                    '''
                }
            }
        }

        // -----------------------------
        // PUSH TO DOCKER HUB
        // -----------------------------
        stage('Push Images') {
            steps {
                sh '''
                docker push $DOCKER_USER/eureka-service
                docker push $DOCKER_USER/config-service
                docker push $DOCKER_USER/gateway-service

                docker push $DOCKER_USER/product-service
                docker push $DOCKER_USER/catalog-service
                docker push $DOCKER_USER/inventory-service
                docker push $DOCKER_USER/pricing-service

                docker push $DOCKER_USER/cart-service
                docker push $DOCKER_USER/recommendation-service
                '''
            }
        }

        // -----------------------------
        // INIT SWARM
        // -----------------------------
        stage('Init Swarm') {
            steps {
                sh '''
                docker info | grep -q "Swarm: active" || docker swarm init
                '''
            }
        }

        // -----------------------------
        // REMOVE OLD STACK
        // -----------------------------
        stage('Remove Old Stack') {
            steps {
                sh '''
                docker stack rm $STACK_NAME || true
                sleep 15
                '''
            }
        }

        // -----------------------------
        // DEPLOY STACK
        // -----------------------------
        stage('Deploy to Swarm') {
            steps {
                sh '''
                docker stack deploy -c docker-stack.yml $STACK_NAME
                '''
            }
        }

        // -----------------------------
        // VERIFY DEPLOYMENT
        // -----------------------------
        stage('Verify Deployment') {
            steps {
                sh '''
                docker stack services $STACK_NAME
                docker stack ps $STACK_NAME
                '''
            }
        }

        // -----------------------------
        // CHECK LOGS (OPTIONAL)
        // -----------------------------
        stage('Check Logs') {
            steps {
                sh '''
                docker service logs ${STACK_NAME}_gateway --tail 20 || true
                '''
            }
        }
    }

    post {
        success {
            echo "🚀 CI/CD + Docker Hub + Swarm deployment SUCCESS!"
        }
        failure {
            echo "❌ Pipeline failed — check logs"
        }
    }
}
