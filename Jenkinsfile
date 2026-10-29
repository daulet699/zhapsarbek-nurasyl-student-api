pipeline {
    agent any

    environment {
        STUDENT_NAME = 'Nurasyl Zhapsarbek'
        STUDENT_GROUP = 'IT2-2308'
        STUDENT_ID = '37841'
        DOCKER_IMAGE = 'zhapsarbek-nurasyl-student-api:v1.0-zhapsarbek-nurasyl-37841'
        PATH = "/Users/nurasylzapsarbek/.docker/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'git log -1 --oneline'
                echo "Student: ${env.STUDENT_NAME} | Group: ${env.STUDENT_GROUP} | ID: ${env.STUDENT_ID}"
            }
        }
        stage('Build') {
            steps {
                sh '''
                    export JAVA_HOME=$(/usr/libexec/java_home -v 21)
                    export PATH="$JAVA_HOME/bin:$PATH"
                    java -version
                    chmod +x ./gradlew
                    ./gradlew --no-daemon classes
                '''
            }
        }
        stage('Test') {
            steps {
                sh '''
                    export JAVA_HOME=$(/usr/libexec/java_home -v 21)
                    export PATH="$JAVA_HOME/bin:$PATH"
                    ./gradlew --no-daemon test
                '''
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t "$DOCKER_IMAGE" .'
            }
        }
        stage('Verify') {
            steps {
                sh '''
                    docker image inspect "$DOCKER_IMAGE" >/dev/null
                    echo "VERIFIED: $DOCKER_IMAGE"
                    echo "Student: $STUDENT_NAME | $STUDENT_GROUP | $STUDENT_ID"
                '''
            }
        }
    }
}
