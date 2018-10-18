package com.greenox.pos.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;

@Component
public class DomainAwarePermissionEvaluator implements PermissionEvaluator {
    private static final Logger LOG = LoggerFactory.getLogger(DomainAwarePermissionEvaluator.class);

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        LOG.info(String.format("check permission '%s' for user '%s' for target '%s'", permission, authentication.getName(), targetDomainObject));
//        if ("place-pos".equals(permission))
//        {
//            Order pos = (Order) targetDomainObject;
//            if (pos.getAmount() > 500)
//            {
//                return hasRole("ROLE_ADMIN", authentication);
//            }
//        }
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPermission(authentication, new DomainObjectReference(targetId, targetType), permission);
    }

    private boolean hasRole(String role, Authentication auth) {

        if (auth == null || auth.getPrincipal() == null) {
            return false;
        }
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if (CollectionUtils.isEmpty(authorities)) {
            return false;
        }
        return authorities.stream().anyMatch(ga -> role.equals(ga.getAuthority()));
    }

    static class DomainObjectReference {
        private final Serializable targetId;
        private final String targetType;

        public DomainObjectReference(Serializable targetId, String targetType) {
            this.targetId = targetId;
            this.targetType = targetType;
        }
    }
}