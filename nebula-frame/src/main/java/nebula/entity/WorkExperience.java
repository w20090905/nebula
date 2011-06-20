package nebula.entity;

public interface WorkExperience {

    Person getPerson();

    String getFrom();

    void setFrom(String from);

    String getTo();

    void setTo(String to);

    Long getIndex();

    void setIndex(Long index);

    String getCompany_name();

    void setCompany_name(String company_name);

    Company getCompany();

    void setCompany(Company company);

}