/*
 * SonarQube AsyncAPI Plugin
 * Copyright (C) 2018-2019 Societe Generale
 * vincent.girard-reydet AT socgen DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package apiquality.sonar.asyncapi.checks.format;

import com.google.common.collect.Sets;
import com.sonar.sslr.api.AstNodeType;
import org.sonar.check.Rule;
import org.apiaddicts.apitools.dosonarapi.api.v4.AsyncApiGrammar;
import apiquality.sonar.asyncapi.checks.BaseCheck;
import org.apiaddicts.apitools.dosonarapi.sslr.yaml.grammar.JsonNode;

import java.util.Set;

@Rule(key = AAR016ContactPropertiesCheck.CHECK_KEY)
public class AAR016ContactPropertiesCheck extends BaseCheck {
    public static final String CHECK_KEY = "AAR016";

    @Override
    public Set<AstNodeType> subscribedKinds() {
        return Sets.newHashSet(AsyncApiGrammar.INFO);
    }

    @Override
    protected void visitNode(JsonNode node) {
        JsonNode contactNode = node.at("/contact");

        if (contactNode.isMissing() || contactNode.isNull()) {
            return;  // If the contact node is missing or null, exit early
        }

        // Check if any of the required properties are missing or null
        boolean nameMissing = contactNode.get("name").isMissing() || contactNode.get("name").isNull();
        boolean urlMissing = contactNode.get("url").isMissing() || contactNode.get("url").isNull();
        boolean emailMissing = contactNode.get("email").isMissing() || contactNode.get("email").isNull();

        // If any property is missing, add an issue
        if (nameMissing || urlMissing || emailMissing) {
            addIssue(CHECK_KEY, translate("AAR016.error"), contactNode.key());
        }
    }
}
