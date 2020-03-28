<#import "parts/commonMacro.ftl" as c>
<#import "parts/loginMacro.ftl" as l>

<@c.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <@l.login "/login" false/>
</@c.page>