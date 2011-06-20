package nebula.entity;

import java.util.ArrayList;
import java.util.List;

public class PersonImp implements Person {
    String name;
    String sex;
    long height;
    String birthday;

    Company company;
    String company_name;

    ArrayList<WorkExperience> workExperiences = new ArrayList<PersonImp.WorkExperience>();

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getWorkExperiences()
     */
    @Override
    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getCompany_name()
     */
    @Override
    public String getCompany_name() {
        return company_name;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#setCompany_name(java.lang.String)
     */
    @Override
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getCompany()
     */
    @Override
    public Company getCompany() {
        return company;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#setCompany(nebula.entity.Company)
     */
    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getSex()
     */
    @Override
    public String getSex() {
        return sex;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#setSex(java.lang.String)
     */
    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getHeight()
     */
    @Override
    public long getHeight() {
        return height;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#setHeight(long)
     */
    @Override
    public void setHeight(long height) {
        this.height = height;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#getBirthday()
     */
    @Override
    public String getBirthday() {
        return birthday;
    }

    /* (non-Javadoc)
     * @see nebula.entity.IPerson#setBirthday(java.lang.String)
     */
    @Override
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public class WorkExperience {
        Long index;
        String from;
        String to;
        String company_name;
        Company company;

        public Person getPerson() {
            return PersonImp.this;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public Long getIndex() {
            return index;
        }

        public void setIndex(Long index) {
            this.index = index;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

    }
}
