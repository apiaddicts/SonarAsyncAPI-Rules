

# 🛠️ SonarAsyncAPI (Rules) ![Release](https://img.shields.io/badge/release-1.0.3-purple) ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white)  [![License: LGPL v3](https://img.shields.io/badge/license-LGPL_v3-blue.svg)](https://www.gnu.org/licenses/lgpl-3.0) 

This repository contains a set of custom SonarQube rules specifically designed to analyze and improve the quality of AsyncAPI specifications. By integrating these rules, teams can ensure best practices, maintainability, and consistency in their API definitions.

### This repository is intended for :octocat: **community** use, it can be modified and adapted without commercial use. If you need a version, support or help for your **enterprise** or project, please contact us 📧 devrel@apiaddicts.org
### 💡 If you have an idea for a rule but you are not sure that everyone needs it you can implement a [custom rule](CustomRules.md) available only for you.

[![Twitter](https://img.shields.io/badge/Twitter-%23000000.svg?style=for-the-badge&logo=x&logoColor=white)](https://twitter.com/APIAddicts) 
[![Discord](https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/ZdbGqMBYy8)
[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/company/apiaddicts/)
[![Facebook](https://img.shields.io/badge/Facebook-%231877F2.svg?style=for-the-badge&logo=Facebook&logoColor=white)](https://www.facebook.com/apiaddicts)
[![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?style=for-the-badge&logo=YouTube&logoColor=white)](https://www.youtube.com/@APIAddictslmaoo)

# 🙌 Join the **doSonarApi** Adopters list 
📢 If doSonarApi is part of your organization's toolkit, we kindly encourage you to include your company's name in our Adopters list. 🙏 This not only significantly boosts the project's visibility and reputation but also represents a small yet impactful way to give back to the project.

| Organization  | Description of Use / Referenc |
|---|---|
|  [CloudAppi](https://cloudappi.net/)  | Apification and generation of microservices |
| [Madrid Digital](https://www.comunidad.madrid/servicios/sede-electronica/madrid-digital/)  | Generation of microservices  |
| [Apiquality](https://apiquality.io/)  | Generation of microservices  |

# 👩🏽‍💻  Contribute to ApiAddicts 

We're an inclusive and open community, welcoming you to join our effort to enhance ApiAddicts, and we're excited to prioritize tasks based on community input, inviting you to review and collaborate through our GitHub issue tracker.

Feel free to drop by and greet us on our GitHub discussion or Discord chat. You can also show your support by giving us some GitHub stars ⭐️, or by following us on Twitter, LinkedIn, and subscribing to our YouTube channel! 🚀

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/apiaddicts)


# 📑 Getting started 

## 🔍 Configure scanner

### Maven plugin

#### Configure properties

In `pom.xml` configure:

````xml
    <properties>
        <!-- Optional, When is set only the language specified is analyzed -->
        <sonar.language>asyncapi</sonar.language>
        <!-- Optional, Default value is src/main,pom.xml -->
        <sonar.sources>.</sonar.sources>
    </properties>
````

#### Run scanner

`mvn sonar:sonar -Dsonar.host.url=<HOST> -Dsonar.login=<KEY>`

### External `sonar-scanner`

#### Install `sonar-scanner`

Download the `sonar-scanner` from https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/ and make it accessible.

#### Configure properties

In `sonar-project.properties` (file in root project folder) configure:

````properties
# must be unique in a given SonarQube instance
sonar.projectKey=test:test
# this is the name and version displayed in the SonarQube UI. Was mandatory prior to SonarQube 6.1.
sonar.projectName=AsyncAPI plugin tests
sonar.projectVersion=1.0-SNAPSHOT

# Path is relative to the sonar-project.properties file. Replace "\" by "/" on Windows.
# This property is optional if sonar.modules is set.
sonar.sources=.

# Encoding of the source code. Default is default system encoding
sonar.sourceEncoding=UTF-8
# Select the language to use for analysis
sonar.language=asyncapi
````

#### ▶️ Run scanner

`sonar-scanner -Dsonar.host.url=<HOST> -Dsonar.login=<KEY>`

## ✅ Compatibility

This plugin is supported by SonarQube versions greater or equal to `6.7.4`

### Explicit compatibility versions tested

| Version |
|---------|
| `6.7.4` |
| `7.9-community` |
| `8.3-community` |

## 📑 Current Rules

- **AAR001MandatoryHttpsProtocolCheck**: Protocol https is mandatory.
- **AAR008DefinedServerCheck**: Define 'servers' is mandatory.
- **AAR009DeclaredTagCheck**: Associate a tag to this operation.
- **AAR010DocumentedTagCheck**: Tags should be documented.
- **AAR011DefinedLicenseCheck**: License should be documented.
- **AAR012DeclaredOperationIDCheck**: Each operation should have a unique operator (Operation ID).
- **AAR013DuplicateOperationIDCheck**: There cannot be two unique operations (OperationID) that are the same.
- **AAR015UndefiendContactCheck**: API should indicate the contact in the info object.
- **AAR016ContactPropertiesCheck**: Contact should contain name, url, and email fields.
- **AAR017UndefinedUrlLicenseCheck**: The license object must have the url field.
- **AAR018SecuritySchemasCheck**: The security scheme must be among those allowed by the organization and must be complete.
- **AAR019IDSchemasCheck**: The identifier must be defined.
- **AAR021ProvideOpSummaryCheck**: Provide a summary for each operation.
- **AAR022DescriptionDiffersSummaryCheck**: Operation description must differ from its summary.
- **AAR024MessageValidationCheck**: All messages sent and received must comply with the message schema specified in the documentation.
- **AAR026MessageSchemasCheck**: Message schemas are recommended to be found in components.
- **AAR029MandatoryDescriptionCheck**: Each channel and each operation must have a description that explains its purpose and function.
- **AAR031MessageExamplesCheck**: All examples in message object should follow payload and headers schemas.
- **AAR032NumericParameterIntegrityCheck**: Numeric parameters should have minimum, maximum, or format restriction.
- **AAR033StringParameterIntegrityCheck**: String parameters should have minLength, maxLength, pattern (regular expression), or enum restriction.
- **AAR034NumericFormatCheck**: Numeric types require a valid format.
- **AAR035MessageTitleCheck**: It is recommended to have a title per message.
- **AAR036BadDescriptionCheck**: The description must begin with the first capital letter and end with a point.
- **AAR037BindingVersionCheck**: You must specify the version of the binding.
- **AAR040DefinedChannelServersCheck**: Channel server must be defined in the servers object.
- **AAR041ComponetChannelServerCheck**: It is recommended to add the servers and channels to component.
- **AAR042MessageIdentifierCheck**: It is recommended to have a unique identifier per message.
- **AAR043SecurityChannelCheck**: It is recommended to add the security scheme to be used to each channel.

## 💛 Sponsors
<img src="https://apiaddicts.cloudappi.net/web/image/4248/LOGOCloudappi2020Versiones-01.png" alt="cloudappi" width="150"/>
<img src="https://www.comunidad.madrid/sites/default/files/styles/block_teaser_image/public/img/logos-simbolos/logo_centrado_md.png?itok=4rTUhmcj" alt="md" width="150"/>
<img src="https://apiquality.io/wp-content/uploads/2022/09/cropped-logo-apiquality-principal-1-170x70.png" height = "75">
<img src="https://apiaddicts-web.s3.eu-west-1.amazonaws.com/wp-content/uploads/2022/03/17155736/cropped-APIAddicts-logotipo_rojo.png" height = "75">