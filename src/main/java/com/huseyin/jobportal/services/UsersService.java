package com.huseyin.jobportal.services;

import com.huseyin.jobportal.entity.JobSeekerProfile;
import com.huseyin.jobportal.entity.RecruiterProfile;
import com.huseyin.jobportal.entity.Users;
import com.huseyin.jobportal.repository.JobSeekerProfileRepository;
import com.huseyin.jobportal.repository.RecruiterProfileRepository;
import com.huseyin.jobportal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;


    @Autowired
    public UsersService(UsersRepository usersRepository,
                        JobSeekerProfileRepository jobSeekerProfileRepository,
                        RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if (userTypeId == 1){
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));

        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<Users> getUserByEmail (String email){
        return usersRepository.findByEmail(email);
    }
}
