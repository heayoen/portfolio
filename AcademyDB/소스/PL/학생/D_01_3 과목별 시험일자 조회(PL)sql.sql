--D_01_3 과목별 시험일자 조회(PL)


create or replace procedure procSubTestdate(
    psugang_num number
)
is
    vsubname    tblSubjectName.subname%type;
    vtestdate   tblTestDate.test_date%type;
    vtest        tblTestName.test%type;
    vtest_type   tblTestName.test_type%type;
    
    cursor vcursor
    is
    select 
        sn.subname,
        td.test_date, 
        tn.test,
        tn.test_type
        
    from tblSugang s
        inner join tblCourse c on s.course_seq = c.course_seq --과정
            inner join tblCsub cs on c.course_seq = cs.course_seq --과정내 과목
                left outer join tblTestDate td on cs.csub_seq = td.csub_seq -- 과목별 시험 
                    inner join tblSubjectName sn on cs.subname_seq = sn.subname_seq --과목명
                        inner join tblTest t on td.testnum_seq = t.testnum_seq
                            inner join tblTestName tn on t.testname_seq = tn.testname_seq
     
        where s.sugang_seq = psugang_num 
            and cs.csend_date < (select max(a.attend_date) from tblAttendance a where a.sugang_seq = psugang_num) 
                order by td.test_date ;

begin
    
    dbms_output.put_line(rpad('과목명', 8, ' ')|| '|  시험일자|        시험문제      |  형식 ');
    dbms_output.put_line('=========================================================');
    
    open vcursor;
    loop
    
        fetch  vcursor into vsubname, vtestdate, vtest, vtest_type;
        exit when vcursor%notfound;
        
        dbms_output.put_line(rpad(vsubname, 8, ' ') ||'|  ' || vtestdate  ||'|  ' || rpad(vtest, 20, ' ')  ||'|  ' || vtest_type);
    
    
    end loop;
    close vcursor;
end;
/


--------------------

begin
    procSubTestdate(180);
end;
/