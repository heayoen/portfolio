-- B-08 관리자_출결 관리 및 출결 조회.sql

--====================================================================================================================================
-- 출결현황을 날짜별로 조회할 수 있어야한다.
--------------------------------------------------------------------------------------------------------------------------------------

-- 연도 조회
create or replace procedure procYearAtt (
    pdate in varchar2
)
is
    mm_name tblMember.m_name%type;
    aatten number;
    late number;
    early_departure number;
    outing number;
    sick_leave number;
    etc number;
    cursor vcursor is
select 
    distinct
    m.m_name,
    (select count(*) from tblattendance where attendance_type = '정상' and sugang_seq = s.sugang_seq and to_char(attend_date, 'yy') = pdate) as 정상,
    (select count(*) from tblattendance where attendance_type = '지각' and sugang_seq = s.sugang_seq and to_char(attend_date, 'yy') = pdate) as 지각,
    (select count(*) from tblattendance where attendance_type = '조퇴' and sugang_seq = s.sugang_seq and to_char(attend_date, 'yy') = pdate) as 조퇴,
    (select count(*) from tblattendance where attendance_type = '외출' and sugang_seq = s.sugang_seq and to_char(attend_date, 'yy') = pdate) as 외출,
    (select count(*) from tblattendance where attendance_type = '병가' and sugang_seq = s.sugang_seq and to_char(attend_date, 'yy') = pdate) as 병가,
    (select count(*) from tblattendance where attendance_type = '기타' and sugang_seq = s.sugang_seq and to_char(attend_date, 'yy') = pdate) as 기타
from tblcourse c
    inner join tblcoursename cn
        on c.cname_seq = cn.cname_seq
            inner join tblsugang s
                on c.course_seq = s.course_seq
                    inner join tblmember m
                        on s.member_seq = m.member_seq
                            inner join tblattendance a
                                on s.sugang_seq = a.sugang_seq
                                    order by m.m_name;

    
begin
    
    open vcursor;
        dbms_output.put_line('=====================================================');
        dbms_output.put_line(' ' || rpad('이름', 10, ' ') || '  ' || rpad('정상', 4, ' ') || '   ' || rpad('지각', 4, ' ') || '   ' || rpad('조퇴', 4, ' ') || '   ' || rpad('외출', 4, ' ') || '   ' || rpad('병가', 4, ' ') || '   ' || rpad('기타', 4, ' '));
        dbms_output.put_line('=====================================================');
        loop
            fetch vcursor into mm_name, aatten, late, early_departure, outing, sick_leave, etc;
            exit when vcursor%notfound;
                dbms_output.put_line(rpad(mm_name, 10, ' ') || '   ' || rpad(aatten, 3, ' ') || '     ' || rpad(late, 3, ' ') || '     ' || rpad(early_departure, 3, ' ') || '   ' || rpad(outing, 3, ' ') || '    ' || rpad(sick_leave, 3, ' ') || '    ' || rpad(etc, 3, ' '));
        
        end loop;
    close vcursor;
    
exception
    when others then
        dbms_output.put_line('==============================================================================================================');
        dbms_output.put_line('                                        잘못된 값을 입력했습니다.');
        dbms_output.put_line('==============================================================================================================');  
    
end procYearAtt;
--------------------------------------------------------------------------------------------------------------------------------------

begin
    procYearAtt('22');
end;

set serverout on;
--====================================================================================================================================

-- 월 조회
create or replace procedure procMonthAtt (
    pdate in varchar2
)
is
    mm_name tblMember.m_name%type;
    aatten number;
    late number;
    early_departure number;
    outing number;
    sick_leave number;
    etc number;
    cursor vcursor is
select 
    distinct
    m.m_name,
    (select count(*) from tblattendance where attendance_type = '정상' and sugang_seq = s.sugang_seq and to_char(attend_date, 'mm') = pdate) as 정상,
    (select count(*) from tblattendance where attendance_type = '지각' and sugang_seq = s.sugang_seq and to_char(attend_date, 'mm') = pdate) as 지각,
    (select count(*) from tblattendance where attendance_type = '조퇴' and sugang_seq = s.sugang_seq and to_char(attend_date, 'mm') = pdate) as 조퇴,
    (select count(*) from tblattendance where attendance_type = '외출' and sugang_seq = s.sugang_seq and to_char(attend_date, 'mm') = pdate) as 외출,
    (select count(*) from tblattendance where attendance_type = '병가' and sugang_seq = s.sugang_seq and to_char(attend_date, 'mm') = pdate) as 병가,
    (select count(*) from tblattendance where attendance_type = '기타' and sugang_seq = s.sugang_seq and to_char(attend_date, 'mm') = pdate) as 기타
from tblcourse c
    inner join tblcoursename cn
        on c.cname_seq = cn.cname_seq
            inner join tblsugang s
                on c.course_seq = s.course_seq
                    inner join tblmember m
                        on s.member_seq = m.member_seq
                            inner join tblattendance a
                                on s.sugang_seq = a.sugang_seq
                                    order by m.m_name;

    
begin
    
    open vcursor;
        dbms_output.put_line('=====================================================');
        dbms_output.put_line(' ' || rpad('이름', 10, ' ') || '  ' || rpad('정상', 4, ' ') || '   ' || rpad('지각', 4, ' ') || '   ' || rpad('조퇴', 4, ' ') || '   ' || rpad('외출', 4, ' ') || '   ' || rpad('병가', 4, ' ') || '   ' || rpad('기타', 4, ' '));
        dbms_output.put_line('=====================================================');
        loop
            fetch vcursor into mm_name, aatten, late, early_departure, outing, sick_leave, etc;
            exit when vcursor%notfound;
                dbms_output.put_line(rpad(mm_name, 10, ' ') || '   ' || rpad(aatten, 3, ' ') || '     ' || rpad(late, 3, ' ') || '     ' || rpad(early_departure, 3, ' ') || '   ' || rpad(outing, 3, ' ') || '    ' || rpad(sick_leave, 3, ' ') || '    ' || rpad(etc, 3, ' '));
        
        end loop;
    close vcursor;
    
exception
    when others then
        dbms_output.put_line('==============================================================================================================');
        dbms_output.put_line('                                        잘못된 값을 입력했습니다.');
        dbms_output.put_line('==============================================================================================================');  
    
end procMonthAtt;
--------------------------------------------------------------------------------------------------------------------------------------

begin
    procMonthAtt('03');
end;

set serverout on;
--====================================================================================================================================

-- 날짜 조회
create or replace procedure procDayAtt (
    pdate in varchar2
)
is
    mm_name tblMember.m_name%type;
    aatten tblattendance.attend_date%type;
    aattendance_type tblattendance.attendance_type%type;
    cursor vcursor is
select 
    distinct
    m.m_name,
    a.attend_date,
    a.attendance_type
from tblcourse c
    inner join tblcoursename cn
        on c.cname_seq = cn.cname_seq
            inner join tblsugang s
                on c.course_seq = s.course_seq
                    inner join tblmember m
                        on s.member_seq = m.member_seq
                            inner join tblattendance a
                                on s.sugang_seq = a.sugang_seq
                                    where a.attend_date = pdate
                                        order by m.m_name;  
begin
    
    open vcursor;
        dbms_output.put_line('=====================================================');
        dbms_output.put_line(' ' || rpad('이름', 10, ' ') || '  ' || rpad('날짜', 10, ' ') || '   ' || rpad('출결', 10, ' ') );
        dbms_output.put_line('=====================================================');
        loop
            fetch vcursor into mm_name, aatten, aattendance_type;
            exit when vcursor%notfound;
                dbms_output.put_line(rpad(mm_name, 10, ' ') || '   ' || rpad(aatten, 10, ' ') || '     ' || rpad(aattendance_type, 10, ' '));
        
        end loop;
    close vcursor;
    
exception
    when others then
        dbms_output.put_line('==============================================================================================================');
        dbms_output.put_line('                                        잘못된 값을 입력했습니다.');
        dbms_output.put_line('==============================================================================================================');  
    
end procDayAtt;
--------------------------------------------------------------------------------------------------------------------------------------

begin
    procDayAtt('22-03-03');
end;

set serverout on;
--====================================================================================================================================

--====================================================================================================================================
-- 특정 개설과정을 선택하는 경우 모든 교육생의 출결을 조회할 수 있어야 한다.
--------------------------------------------------------------------------------------------------------------------------------------

create or replace procedure procCourseAtt (
    pcourse_seq tblCourse.course_seq%type
)
is
    mm_name tblMember.m_name%type;
    aatten number;
    late number;
    early_departure number;
    outing number;
    sick_leave number;
    etc number;
    cursor vcursor is
    select 
        distinct
        m.m_name,
        (select count(*) from tblattendance where attendance_type = '정상' and sugang_seq = s.sugang_seq) as 정상,
        (select count(*) from tblattendance where attendance_type = '지각' and sugang_seq = s.sugang_seq) as 지각,
        (select count(*) from tblattendance where attendance_type = '조퇴' and sugang_seq = s.sugang_seq) as 조퇴,
        (select count(*) from tblattendance where attendance_type = '외출' and sugang_seq = s.sugang_seq) as 외출,
        (select count(*) from tblattendance where attendance_type = '병가' and sugang_seq = s.sugang_seq) as 병가,
        (select count(*) from tblattendance where attendance_type = '기타' and sugang_seq = s.sugang_seq) as 기타
    from tblcourse c
        inner join tblcoursename cn
            on c.cname_seq = cn.cname_seq
                inner join tblsugang s
                    on c.course_seq = s.course_seq
                        inner join tblmember m
                            on s.member_seq = m.member_seq
                                inner join tblattendance a
                                    on s.sugang_seq = a.sugang_seq
                                        where c.course_seq = pcourse_seq;

    
begin
    
    open vcursor;
        dbms_output.put_line('=====================================================');
        dbms_output.put_line(' ' || rpad('이름', 10, ' ') || '  ' || rpad('정상', 4, ' ') || '   ' || rpad('지각', 4, ' ') || '   ' || rpad('조퇴', 4, ' ') || '   ' || rpad('외출', 4, ' ') || '   ' || rpad('병가', 4, ' ') || '   ' || rpad('기타', 4, ' '));
        dbms_output.put_line('=====================================================');
        loop
            fetch vcursor into mm_name, aatten, late, early_departure, outing, sick_leave, etc;
            exit when vcursor%notfound;
                dbms_output.put_line(rpad(mm_name, 10, ' ') || '   ' || rpad(aatten, 3, ' ') || '     ' || rpad(late, 3, ' ') || '     ' || rpad(early_departure, 3, ' ') || '   ' || rpad(outing, 3, ' ') || '    ' || rpad(sick_leave, 3, ' ') || '    ' || rpad(etc, 3, ' '));
        
        end loop;
    close vcursor;
    
exception
    when others then
        dbms_output.put_line('==============================================================================================================');
        dbms_output.put_line('                                        잘못된 값을 입력했습니다.');
        dbms_output.put_line('==============================================================================================================');  
    
end procCourseAtt;

--------------------------------------------------------------------------------------------------------------------------------------

begin
    procCourseAtt(25);
end;

set serverout on;
--====================================================================================================================================









--====================================================================================================================================
-- 특정인원의 출결 현황 조회
--------------------------------------------------------------------------------------------------------------------------------------

create or replace procedure procMemAtt (
    pcourse_seq tblCourse.course_seq%type
)
is
    mm_name tblMember.m_name%type;
    aatten number;
    late number;
    early_departure number;
    outing number;
    sick_leave number;
    etc number;
    cursor vcursor is
    select 
        distinct
        m.m_name,
        (select count(*) from tblattendance where attendance_type = '정상' and sugang_seq = s.sugang_seq) as 정상,
        (select count(*) from tblattendance where attendance_type = '지각' and sugang_seq = s.sugang_seq) as 지각,
        (select count(*) from tblattendance where attendance_type = '조퇴' and sugang_seq = s.sugang_seq) as 조퇴,
        (select count(*) from tblattendance where attendance_type = '외출' and sugang_seq = s.sugang_seq) as 외출,
        (select count(*) from tblattendance where attendance_type = '병가' and sugang_seq = s.sugang_seq) as 병가,
        (select count(*) from tblattendance where attendance_type = '기타' and sugang_seq = s.sugang_seq) as 기타
    from tblcourse c
        inner join tblcoursename cn
            on c.cname_seq = cn.cname_seq
                inner join tblsugang s
                    on c.course_seq = s.course_seq
                        inner join tblmember m
                            on s.member_seq = m.member_seq
                                inner join tblattendance a
                                    on s.sugang_seq = a.sugang_seq
                                        where s.sugang_seq = pcourse_seq;

    
begin
    
    open vcursor;
        dbms_output.put_line('=====================================================');
        dbms_output.put_line(' ' || rpad('이름', 10, ' ') || '  ' || rpad('정상', 4, ' ') || '   ' || rpad('지각', 4, ' ') || '   ' || rpad('조퇴', 4, ' ') || '   ' || rpad('외출', 4, ' ') || '   ' || rpad('병가', 4, ' ') || '   ' || rpad('기타', 4, ' '));
        dbms_output.put_line('=====================================================');
        loop
            fetch vcursor into mm_name, aatten, late, early_departure, outing, sick_leave, etc;
            exit when vcursor%notfound;
                dbms_output.put_line(rpad(mm_name, 10, ' ') || '   ' || rpad(aatten, 3, ' ') || '     ' || rpad(late, 3, ' ') || '     ' || rpad(early_departure, 3, ' ') || '   ' || rpad(outing, 3, ' ') || '    ' || rpad(sick_leave, 3, ' ') || '    ' || rpad(etc, 3, ' '));
        
        end loop;
    close vcursor;
    
exception
    when others then
        dbms_output.put_line('==============================================================================================================');
        dbms_output.put_line('                                        잘못된 값을 입력했습니다.');
        dbms_output.put_line('==============================================================================================================');  
    
end procMemAtt;

--------------------------------------------------------------------------------------------------------------------------------------

begin
    procMemAtt(185);
end;

--====================================================================================================================================










