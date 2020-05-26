package ivan.json.service;

import ivan.json.domain.dtos.UserSeedDto;

public interface UserService {

    void seedUsers(UserSeedDto[] userSeedDtos);
}
