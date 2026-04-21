// vars/loadConfig.groovy
def call(String environment = 'dev') {
    // Define configurations for different environments
    def configs = [
        dev: [
            region: 'us-east-1',
            log_level: 'DEBUG',
            lambda_memory: 128,
            slack_channel: '#dev-builds'
        ],
        prod: [
            region: 'us-west-2',
            log_level: 'INFO',
            lambda_memory: 1024,
            slack_channel: '#prod-builds'
        ]
    ]

    // Return the config for the requested environment, default to dev
    return configs[environment] ?: configs.dev
}
