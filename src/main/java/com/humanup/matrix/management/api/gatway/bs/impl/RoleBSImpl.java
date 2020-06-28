package com.humanup.matrix.management.api.gatway.bs.impl;
import com.humanup.matrix.management.api.gatway.aop.dto.AuthorizationException;
import com.humanup.matrix.management.api.gatway.aop.dto.RoleException;
import com.humanup.matrix.management.api.gatway.bs.RoleBS;
import com.humanup.matrix.management.api.gatway.bs.impl.sender.RabbitMQAuthorizationSender;
import com.humanup.matrix.management.api.gatway.bs.impl.sender.RabbitMQRoleSender;
import com.humanup.matrix.management.api.gatway.dao.RoleDAO;
import com.humanup.matrix.management.api.gatway.dao.entities.Role;
import com.humanup.matrix.management.api.gatway.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RoleBSImpl implements RoleBS {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private RabbitMQRoleSender rabbitMQRoleSender;
    @Override
    public boolean createRole(RoleVO roleVO) throws RoleException {
        if (null == roleVO) throw new RoleException();
        rabbitMQRoleSender.send(roleVO);
        return true;
    }

    @Override
    public RoleVO findByRoleTitle(String roleTitle) {
        Optional<Role> roleFinded = Optional.ofNullable(roleDAO.findByRoleTitle(roleTitle));
        if(roleFinded.isPresent()) {
            return  RoleVO.builder()
                    .roleTitle(roleFinded.get().getRoleTitle())
                    .roleDescription(roleFinded.get().getRoleDescription())
                    .build();
        }
        return null;
    }

    @Override
    public List<RoleVO> findListRole() {
        return roleDAO.findAll()
                .stream()
                .map(roleFinded ->  RoleVO.builder()
                        .roleTitle(roleFinded.getRoleTitle())
                        .roleDescription(roleFinded.getRoleDescription())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public List<RoleVO> findListAuthorizationsByAuthorizationsTitle(String authorizationsTitle) {
        return roleDAO.findListRolesByTitle(authorizationsTitle)
                .stream()
                .map(roleFinded ->  RoleVO.builder()
                        .roleTitle(roleFinded.getRoleTitle())
                        .roleDescription(roleFinded.getRoleDescription())
                        .build())
                .collect(Collectors.toList());
    }

}
