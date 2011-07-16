package nebula.entity;

import java.util.List;

import nebula.entity.PersonImp.WorkExperienceImp;

public interface Person {

    String getName();

    void setName(String name);

    String getSex();

    void setSex(String sex);

    long getHeight();

    void setHeight(long height);

    String getBirthday();

    void setBirthday(String birthday);
    
     List<WorkExperienceImp> getWorkExperiences();

     String getCompany_name();

     void setCompany_name(String company_name);

     Company getCompany();

     void setCompany(Company company);


}