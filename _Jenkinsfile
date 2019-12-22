#!/usr/bin/env groovy

pipeline {
    agent {
        label 'jdk-11'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        ansiColor('xterm')
    }

    stages {

        stage('Compile') {
            steps {
                quietSh './gradlew clean version classes'
            }
        }

        stage('Test') {
            steps {
                quietSh './gradlew test'
            }
        }

        stage('Publish') {
            environment {
                REPO = credentials('repo')
            }

            steps {
                quietSh "./gradlew artifactoryPublish -Djgitver.branch=$BRANCH_NAME -PrepoUsername=$REPO_USR -PrepoPassword=$REPO_PSW"
            }
        }
    }

    post {
        failure {
            junit 'build/test-results/**/*.xml'
        }
        changed {
            sendNotifications()
        }
    }
}
