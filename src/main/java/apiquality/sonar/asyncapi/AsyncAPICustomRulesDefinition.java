package apiquality.sonar.asyncapi;

import org.sonar.api.ExtensionPoint;
import org.sonar.api.ce.ComputeEngineSide;
import org.sonar.api.scanner.ScannerSide;
import org.sonar.api.server.ServerSide;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;
import org.sonarsource.api.sonarlint.SonarLintSide;

import apiquality.sonar.asyncapi.checks.RulesLists;

@ServerSide
@SonarLintSide
@ComputeEngineSide
@ScannerSide
@ExtensionPoint
public class AsyncAPICustomRulesDefinition implements RulesDefinition {
    public static final String REPOSITORY_KEY = "asyncapi-custom";
    private static final String REPOSITORY_NAME = "AsyncAPI Custom";
    private static final String ROOT_RESOURCE_FOLDER = "org/sonar/l10n/asyncapi/rules/asyncapi/";
    private static final String SECURITY_GROUP = "security";
    private static final String FORMAT_GROUP = "format";
    private static final String SCHEMAS_GROUP = "schemas";
    private static final String EXAMPLES_GROUP = "examples";
    private static final String OWASP_GROUP = "owasp";
    private static final String OPERATIONS_GROUP = "operations";
    private static final String PARAMETERS_GROUP = "parameters";
    private static final String APIM_WSO2_GROUP = "apim/wso2";
    private static final String REGEX_GROUP = "regex"; 

    @Override
    public void define(Context context) {
        I18nContext.initializeFromUserLanguage();
        NewRepository repository = context
                .createRepository(REPOSITORY_KEY, "asyncapi")
                .setName(REPOSITORY_NAME);

        // Carga de reglas para cada grupo
        new RuleMetadataLoader(getPath(EXAMPLES_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getExamplesChcecks());
        new RuleMetadataLoader(getPath(SECURITY_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getSecurityChecks());
        new RuleMetadataLoader(getPath(FORMAT_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getFormatChecks());
        new RuleMetadataLoader(getPath(SCHEMAS_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getSchemasChecks());
        new RuleMetadataLoader(getPath(OWASP_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getOWASPChecks());
        new RuleMetadataLoader(getPath(OPERATIONS_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getOperationsChecks());
        new RuleMetadataLoader(getPath(PARAMETERS_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getParametersChecks());
        new RuleMetadataLoader(getPath(APIM_WSO2_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getWSO2Checks());
        new RuleMetadataLoader(getPath(REGEX_GROUP)).addRulesByAnnotatedClass(repository, RulesLists.getRegexChecks());

        repository.done();
    }

    private void markAsTemplate(NewRepository repository, String ruleKey) {
        if (repository.rule(ruleKey) != null) {
            repository.rule(ruleKey).setTemplate(true);
        }
    }

    private void markAsDeactivated(NewRepository repository, String ruleKey) {
        if (repository.rule(ruleKey) != null) {
            repository.rule(ruleKey).setActivatedByDefault(false);
        }
    }



    private String getPath(String group) {
        String lang = I18nContext.getLang();
        String langFolder = ROOT_RESOURCE_FOLDER.replace("l10n", "l10n" + "/" + lang) + group;
        String folder = ROOT_RESOURCE_FOLDER + group;
        boolean langFolderExists = "es".equals(lang);
        return langFolderExists ? langFolder : folder;
    }
}