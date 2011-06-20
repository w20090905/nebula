package nebula.entity;

import java.util.List;

import nebula.entity.PersonImp.WorkExperience;

public class PersonAgent implements Person {
    PersonImp src;

    PersonAgent(PersonImp src) {
        this.src = src;
    }

    @Override
    public List<WorkExperience> getWorkExperiences() {
        return src.workExperiences;
    }

    @Override
    public String getCompany_name() {
        return src.company_name;
    }

    @Override
    public void setCompany_name(String company_name) {
    }

    @Override
    public Company getCompany() {
        return src.company;
    }

    @Override
    public void setCompany(Company company) {

    }

    @Override
    public String getName() {
        return src.name;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getSex() {
        return src.sex;
    }

    @Override
    public void setSex(String sex) {

    }

    @Override
    public long getHeight() {
        return src.height;
    }

    @Override
    public void setHeight(long height) {

    }

    @Override
    public String getBirthday() {
        return src.birthday;
    }

    @Override
    public void setBirthday(String birthday) {

    }

}
