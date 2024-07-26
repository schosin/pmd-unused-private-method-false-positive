# pmd-unused-private-method-false-positive

False positive UnusedPrivateMethod on method `UnusedPrivateMethod` in UnusedPrivateMethodFalsePositive.

Run `mvn clean verify` to generate PMD report using maven-pmd-plugin:3.24.0 and PMD:7.3.0.


```xml
<?xml version="1.0" encoding="UTF-8"?>
<pmd xmlns="http://pmd.sourceforge.net/report/2.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://pmd.sourceforge.net/report/2.0.0 https://pmd.github.io/schema/report_2_0_0.xsd" version="7.3.0" timestamp="2024-07-26T17:35:55.516">
<file name="C:\...\pmd-unused-private-method-false-positive\src\main\java\de\schosin\pmd\UnusedPrivateMethodFalsePositive.java">
<violation beginline="32" endline="32" begincolumn="20" endcolumn="33" rule="UnusedPrivateMethod" ruleset="Best Practices" package="de.schosin.pmd" class="UnusedPrivateMethodFalsePositive" method="falsePositive" externalInfoUrl="https://docs.pmd-code.org/pmd-doc-7.3.0/pmd_rules_java_bestpractices.html#unusedprivatemethod" priority="3">
Avoid unused private methods such as 'falsePositive(Data, Map&lt;String, List&lt;Data&gt;&gt;)'.
</violation>
</file>
</pmd>
```
