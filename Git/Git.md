## About Git

UNIX는 대형 컴퓨터를 위해 나온 운영체제이다. 리눅스 토발즈라는 사람이 개인용 컴퓨터를 위해 이에 맞는 OS로 발전을 시킨다. 이 것이 LINUX GNU공개 소프트웨어 프로젝트 시스템이다.

GNU란 예를 들어 A란 소프트웨어를 만들었을때, 누구에게나 공개하겠다 라는 뜻. 단, 사용 시 GPL 라이센스를 따라야 한다.

GPL 라이센스는 A 소프트웨어를 사용하여 업그레이드하여 B 소프트웨어를 만들면 무조건 공개되어야 하는 의무를 가진다.

수많은 개발자들이 리눅스(오픈소스 재단)를 계속 발전시켜나갔다. 기존 협업은 서로의 파일을 공유하였다. 이렇게 관리를 하다보니 불편한 사항이 많았다. 그래서 리눅스는 Bitkeeper라는 프로그램을 사용하게 된다.  이것을 DVCS(분산 버전 관리 시스템)이라고 칭한다. 근데 BitKeeper가 상용 프로그램으로 바뀌게 되면서

Bitkeeper의 기술을 바탕으로 보완을 하며 버전관리 시스템 Git을 개발하게 된다. Git은 리눅스에서 만들었기 때문에 GNU이다.

Git을 업그레이드하기 위해서는 리눅스와 동일하게 GPL 라이센스를 따라야 한다.

### About Github

Github은 모든 개발자의 중심이라고 할 수 있는 클라우드 저장소이다. 어떤 프로그래머가 A, B, C 프로젝트를 GIT으로 관리한다고 가정했을때 각기 다른 코드들을 다른 컴퓨터에서 사용하려면 해당 코드들을 USB등을 통해 가져 와야한다. 하지만 Github에 공유한다면 다른 컴퓨터에서도 해당 코드를 손쉽게 관리할 수 있다. Git은 프로그램이며 Github은 코드들을 저장할 수 있는 클라우드 저장소이다.

Github은 Public 과 Private 서비스로 나뉜다. 공개되는 코드들은 MIT 라이센스를 따르게 된다. Github은 Git의 GPL 라이센스를 굳이 따를 필요가 없다. MIT 라이센스는 소스코드를 공개하겠다는 것 헌데 이 소스코드를 가지고 어떤 업그레이드를 해서 개발을 했을 때 해당 코드를 우리가 공개할 의무가 없다.

이런 라이센스를 따르고 있는 Github이 Microsoft에서 인수를 하게 된다. 

### 버전 관리 시스템(VCS)

파일이 하나가 변경이 된 경우 파일에 대한 데이터베이스를 만들어 버전을 관리해준다. 버전1에서 100개의 파일을 들고 있다고 가정했을 때, 일부 파일을 수정하게 되어 버전2로 업그레이드 할 경우 전체 복사를 하는 것이 아닌 변경된 부분 복사를 하게 된다. 이로 인해 용량을 효율적으로 관리할 수 있게 된다. 버전2에서 버전1로 ROLLBACK 하는 것도 가능하다. 이것의 단점은 바이러스에 취약하며 로컬에서만 관리하기 때문에 협업이 힘들다.

협업의 단점을 보완하기 위해 중앙 집중형 버전 관리 시스템 CVCS가 등장하게 된다. 로컬이 아닌 중앙 집중 컴퓨터에 관리하게 된다. 하지만 예를 들어 B 컴퓨터가 해당 내부 파일 file.txt를 수정해서 올려서 덮어 씌웠다고 가정할 때 A 컴퓨터에서 덮어 씌웠다는 사실을 모른 채 (중앙 집중형 컴퓨터는 히스토리를 가지고 있지만 A, B 컴퓨터는 히스토리를 가지고 있지 않다.) 해당 파일을 덮어 쓰는 경우가 생길 수 있다. 이 경우 B 컴퓨터에서 해당 파일을 다시 내려받을 경우 수정된 부분이 전부 소실되게 된다. 그리고 중앙 집중형 컴퓨터가 바이러스나 오류에 노출되었을 경우 타격이 크다. 

이후 중앙 집중 컴퓨터의 역할과 유사한 Github을 물론이며 로컬도 히스토리를 가지고 있는 분산버전 관리 시스템(DVCS)인 Git이 등장하게 된다. 이로 인해 중앙 저장소에 문제가 생겨도 로컬의 히스토리를 통해 복구가 가능해졌다.

### Git 실행 원리

특정 A 폴더를 깃 폴더로 사용하겠다고 선언을 했다.(git init) 이때 A 폴더를 작업 영역(Working Directory)라고 부른다. 이후 text.txt 파일을 만들었다. 이때 Git은 첫 번째로 해당 폴더의 변경을 감지한다. 

두 번째로 기록 여부를 정하게 되는데 add 라는 명령어를 사용시 인덱스 영역이 만들어진다.(git add) 이때 tree라는 것 안에 test.txt가 연결이 된다. tree를 폴더라고 생각하면 된다. test.txt는 BLOB(파일) 객체라고 한다. 

이후 영구적으로 기록을 하고 싶다면 Header라는 박스로 넘기게 된다. Header라는 곳 안에는 branch라는 가지가 존재하는데 그 가지의 최상단에 v1 으로 점을 찍고 관리하게 된다. v1에는 add로 인덱스 영역에 존재하는 것들이 올라가고 영구히 기록되어 관리된다.(git commit) 여기서 test2.txt를 add 하게 되면 인덱스 영역에는 최상위 tree에 test2.txt 그리고 하위 폴더에 v1의 tree가 존재하게 된다. 이후 commit을 하게 되면 v1 아래로 v2라는 점을 찍고 관리하게 된다. 하위 v1 tree는 폴더를 통째로 복제 하는 게 아닌 40자의 해시 값만 갖고 관리를 하게 된다.

새로운 파일이 추가되지 않고 이후 test2.txt가 변경이 될 경우 인덱스 영역에는 최상위 tree에 test2.txt(변경 후) 하위 tree test2.txt(변경 전)을 포함한 v2의 tree가 존재하게 된다.

v3에서 v2로 돌아가고 싶다고 할 때 Header를 v2로 변경하여 v2에 해당하는 tree를 해시 값으로 찾아 복제하여 그대로 들고 올 수 있다.

인덱스 영역의 히스토리가 지워지더라도 백업 로그로 관리가 되고 있기 때문에 복구가 가능하다.
### Ex01

git init -> test1.txt 생성 -> git add . (변경된 모든파일 add) -> git commit -m "첫번째 사진" (헤더로 옮긴다.)

**최초 설정**

```console
$ git config --global user.name "John Doe"
$ git config --global user.email johndoe@example.com
```

### Ex02

git init -> test1.txt 생성 -> git add . -> git commit -m "첫번째 사진" -> test2.txt 생성 -> git add . -> git commit -m "두번째 사진" -> test1.txt 변경 -> git add . -> git commit -m "세번째 사진"

commit을 하면 refs의 heads에 기록이 남는다. 마지막 commit 한 해시코드가 저장되어 있다.

### Ex03

git init -> test1.txt 생성 -> git add . -> git commit -m "1. 첫번째 사진" -> test2.txt 생성 -> git add . -> git commit -m "2. 두두번째 사진" -> git reset --soft `돌아가야할 헤더 해시코드 5자` -> git commit -m "2. 두번째 사진" -> git reset --mixed `돌아가야할 헤더 해시코드 5자` -> git add . -> git commit -m "2. 두번째 사진" -> git reset --hard `돌아가야할 헤더 해시코드 5자`

두두번째 메시지를 되돌리고 싶다.(soft) -> test2를 재변경 하고싶다.(mixed) -> test2 를 날리고 처음부터 만들고 싶다.(hard)

mixed 보다는 새로 add 후 commit을 하는게 낫다.

**Reset**

- Hard : 마지막 헤더, 인덱스, 파일을 포함하여 날리고 `1. 첫번째 사진` 상태로 복귀
- Mixed : add 하기 전으로 복귀 (작업 내용이 재변경이 필요할 때)
- Soft : header 에 올리기 전으로 복귀 (커밋로그 변경시 사용)

test2 를 날렸지만 다시 돌리고 싶을 경우 `git reflog`를 활용한다.

```console
f0c018f (HEAD -> master) HEAD@{0}: commit: 3. 세번째 사진 v2
9f45aab HEAD@{1}: commit: 3. 세번째 사진
79d4009 HEAD@{2}: reset: moving to 79d40
9a4934b HEAD@{3}: commit: 2. 두번째 사진
79d4009 HEAD@{4}: reset: moving to 79d40
18211bb HEAD@{5}: commit: 2. 두번째 사진
79d4009 HEAD@{6}: reset: moving to 79d40
47cb2de HEAD@{7}: commit: 2. 두두번째 사진
79d4009 HEAD@{8}: commit (initial): 1. 첫번째 사진
```

3번으로 돌아가고싶다면 

`git reset --hard 9a493`

### Ex04

git init -> test1.txt 생성 -> git add . -> git commit -m "test3.txt. 생성 완료" -> git commit --amend -m "test1.txt 생성 완료"

git의 최종 로그를 변경하고자 한다.

### Branch

commit을 하게되면 main이라는 branch가 하나 생성된다. 

블로그를 만든다고 가정하여 회원가입 기능을 커밋 후 로그인 기능을 커밋했다고 가정하자.(main branch) 이후 아이디 중복 체크 기능을 추가를 위한 계획 단계에서 idea라는 branch를 새로 만들게 된다. 이후 main branch의 마지막 헤더를 분기점으로 삼는다.

이렇게 되면 idea branch는 회원가입과 로그인에 대한 모든 히스토리를 들고있게 된다. main branch에선 기존 글쓰기 작업에 대해 개발하여 커밋하고 남는 시간에 idea branch에 중복 체크 기능을 구현하여 커밋을 하여 idea branch에서 아이디 중복 체크의 기능이 문제가 없는지 확인을 한다. 사용을 하고자 한다면 main branch로 branch를 합쳐야 하는 상황이 생긴다.

이때 문제는 로그인을 분기점으로 idea branch가 만들어 졌지만 main branch의 헤더는 글쓰기 부분을 가리키고 있다. 결과적으로 회원 가입과 로그인 까지는 동일하지만 이후부터는 각 branch의 형상이 다르다.

이때 로그인은 공통 조상이며, 글쓰기와 아이디 중복 체크는 자식이 된다. 이 세 부분에 중복된 파일이나 문제가 없는지 분석 후에 확인하여 합치게 되는데 이 것을 `3-way merge` 라고 한다.

만약 위의 경우와 다르게 main branch 쪽에 글쓰기라는 추가 작업이 없다고 가정을 한다면, idea branch 쪽의 아이디 중복 체크 완료 부분만 들고 오면 되기 때문에 비교적 쉽다. 이 것을 `fast-forward merge` 라고 한다.

merge 라는 명령어를 사용하면 두 경우를 자동으로 수행하게 되지만 위의 개념을 잘 이해해 두자.

### Branch Pointer

git init 으로 인한 작업 영역에 회원 가입 add 후에 commit을 하게 되면 가지가 하나 생기고 회원 가입 점이 하나 생기고 main branch의 pointer가 그 것을 가리키게 된다. 이후 로그인 추가한다면 main branch pointer는 로그인 쪽으로 이동하게 된다.

이후 아이디 중복 체크를 위한 topic이라는 branch를 만들게 된다.

이때 가지가 새로 생기는 것이 아닌 topic branch pointer가 로그인 부분을 가리키게 된다.

이후 topic branch 에서 아이디 중복 체크를 개발 후 커밋을 했다면 main branch pointer는 로그인 자리에 머물러있고 topic branch pointer는 아이디 중복 체크 완료로 이동하게 된다. 만약 병합을 하고 싶다면 main branch pointer를 topic branch pointer 쪽으로 이동을 시키면 된다. 

이 때 실제로 병합된 것이 아닌 포인터만 이동하였다고 하여 이것을 `fast-forward merge`라고 한다. 

만약 main branch 쪽으로 이동하여 글쓰기를 따로 개발하여 commit 한다고 한다면 이때는 새로운 가지가 생기고 글쓰기 쪽으로 main branch pointer가 이동하게 된다. 이 경우 main branch pointer를 아이디 중복 체크 쪽으로 이동 시켜버린다면, 글쓰기 부분이 날라가게 된다. 이때 `3-way merge`를 하는데 수행하면 글쓰기로 인한 가지 쪽으로 아이디 중복 완료 점이 추가되며 main branch pointer가 이 부분으로 이동하게 된다. topic branch pointer는 변동이 없다.

위의 로그인부터 파생이 되었기 때문에 로그인은 부모가 되며 글쓰기와 아이디 중복 체크는 자식이 된다. 세 개의 점을 merge 하게 되므로 이 것을 `3-way merge`라고 한다.

### Ex05(Fast-forward)

git init -> touch 회원가입.txt -> git add . -> git commit -m "회원가입" -> touch 로그인.txt -> git add . -> git commit -m "로그인" -> git branch topic -> git checkout topic(브랜치 이동) -> touch 아이디중복체크.txt -> git add . -> git commit -m "아이디중복체크" -> git branch master (마스터로 이동하면 아이디중복체크.txt 가 보이지 않는다. 아이디중복체크.txt를 topic branch에서 commit을 했기 때문) -> git merge topic -> Fast-forward 문구 확인

기본적으로 Fast-forward는 merge 로그를 안남기지만 --no-ff 옵션으로 로그를 남길 수 있다. 로그를 남겨주는 것이 좋다.

**HEAD의 위치**

회원가입 (MB) -> 로그인(MB) -> checkout으로 topic으로 이동 -> 로그인(TB) -> 아이디중복체크(TB) -> checkout으로 main으로 이동 시 -> 로그인(MB) -> 메인 위치에서 topic branch merge -> 아이디 중복 체크(MB, TB) 여기서 헤더는 MB를 가리키게 된다.

### Ex06(3-way merge)

git init -> touch 회원가입.txt -> git add . -> git commit -m "회원가입" -> touch 로그인.txt -> git add . -> git commit -m "로그인"  -> git checkout -b topic(생성과 이동 동시에) -> touch 아이디중복체크.txt -> git add . -> git commit -m "아이디중복체크" -> git checkout master -> touch 글쓰기.txt -> git add . -> git commit -m "글쓰기" -> git merge topic -> 로그 여부 :wq

### Merge 충돌

회원가입과 로그인을 커밋하여 master branch pointer가 로그인을 가리키고 있다고 가정하자. 여기서 topic branch를 생성. 이때 로그인 파일을 master branch와 topic branch에서 각각 수정하게 된다. 이 상태에서 두개를 merge 할건데 master 기준으로 합친다고 가정했을 때 `3-way merge`가 일어나는데 이때 충돌이 일어날 수 있다.

### Ex07

git init -> git add . -> git commit -m "로그인" -> git checkout -b topic -> 로그인파일 수정 -> git add . -> git commit -m "로그인 체크박스" -> git checkout master -> 로그인 파일 수정 -> git add . -> git commit -m "로그인 라디오버튼" -> git merge topic (conflict 오류) -> 직접 수정 -> git add . -> git commit -m "로그인 체크박스 충돌 수정"

```
---------------
<<<<<<< HEAD -- 제거
로그인 라디오버튼 -- 제거
======= -- 제거
로그인 체크박스
>>>>>>> topic -- 제거
---------------
```

### Rebase

로그 정리를 하는 것. 네 개의 점을 찍을 것이다. 마음에 안드는 로그들을 정리를 할 것이다.

세 개의 점을 하나로 sqaush 할 것이다. 항상 과거 쪽으로 sqaush 하여야 한다. 과거로부터 순차적으로 Pick -> Sqaush -> Sqaush 순.

`git rebase -i HEAD~3`

### Ex08(Rebase)

git init -> 환경설정.txt 생성 -> git add . -> git commit -m "환경설정완료" -> 로그인퇴근.txt 생성 -> git add . -> git commit -m "로그인퇴근" -> 로그인아파서퇴근.txt 생성 -> git add . -> git commit -m "로그인아파서퇴근" ->  로그인완료.txt 생성 -> git add . -> git commit -m "로그인완료" -> git rebase -i HEAD~3 -> 로그인퇴근, 로그인아파서퇴근 s 수정 -> 이후 필요없는 로그들 지우고 수정

`d` -> 해당 커밋 삭제
`r` -> 해당 로그 수정

## Github

myapp이라는 레파지토리를 만들었을 때, github 컴퓨터(서버) 내부에 myapp이라는 폴더를 하나 만들어서 이쪽으로 접속할 수 있는 
 파일 서버 주소를 만들어준다. `https://github.com/아이디/myapp.git` 이 것을 클라우드 저장소라고 한다.  해당 주소에 git init가 되어있는 상황.

깃헙 클라우드 저장소의 키워드를 origin이라고 한다. origin에 업로드를 하기 위해선 remote라는 원격지를 관리하는 명령어를 써야한다.

로컬엔 master branch 클라우드 저장소에는 origin/master 브랜치 둘다 branch이기 때문에 파일을 업로드만 해서는 안되고 파일 업로드와 병합을 같이 해주어야 한다. 이때 사용하는 명령어가 `push` 이다.

다운로드와 병합을 함께 받아오는 명령어는 `push` 이다

git init과 git remote ~, git pull 을 한꺼번에 받아 올수 있는 명령어가 있는데 `git clone`이다.

### Ex09.Company(push)

myapp 파일 생성 -> git init -> 프로젝트설정.txt 생성 -> git add . -> git commit -m "프로젝트 설정완료" -> git remote add origin `https 주소` -> git ls-remote(연결 확인) -> git push origin master (업로드와 병합)

### Ex09.Home(pull) => fetch + merge

git init -> git remote add origin `https 주소` -> git pull origin master(다운로드와 병합)

연결을 잘못했을 경우 `git remote rm origin`

### Ex09.Pcroom(clone)

git clone `https 주소`

회사에 다시 가서 회원가입을 추가했다고 가정하자.

**Ex09.Company**

회원가입.txt 생성 -> git add . -> git commit -m "회원가입"

이후 집에서 추가적으로 개발을 하려고 한다. 여기서 데이터 동기화가 필요하다.

**Ex09.Home**

git pull origin master -> 로그인.txt 생성 -> git add . -> git commit -m "로그인"

출장지에 가서 글쓰기를 만들려고 한다. 하지만 글쓰기 기능이 오늘 내로 안될 것 같다고 가정한다.

이때 topic branch를 만들어서 Github에 push 한다. 해당 레포지토리에 topic branch가 없다면 새로 만들어준다.

**Ex10.Jeju**

git clone `https 주소` -> myapp 경로로 이동 -> git checkout -b topic -> 글쓰기50프로.txt 생성 -> git add . -> git commit -m "글쓰기50프로" -> git push origin topic

다시 회사로 돌아왔다고 가정하자. 일단 master branch를 pull로 동기화 하고 topic branch를 다운 받아야 한다.

**Ex09.Company**

git pull origin master -> git fetch origin(저장소의 모든 branch 다운) -> git checkout -b topic origin/topic (topic branch를 만듦과 동시에 merge.)
-> 글쓰기완료.txt 생성 -> git add . -> git commit -m "글쓰기완료" -> git checkout master -> git merge --squash topic (커밋이 안된상태로 topic 변경 파일이 넘어온다.) -> git commit -m "글쓰기완료" -> git push origin master

### Git Remote Branch

origin 영역에 branch가 master, dev, topic 세 개가 있다고 가정해보자.

local에서 해당 영역을 동기화 하고자 할 때

1. git init
2. git remote add origin

명령어를 수행하면 Byte Stream이 연결된다. 이후 데이터들을 다운로드 받아야한다.

3. git fetch origin

이때 remote branch라는 영역에 저장소와 동일한 브랜치와 데이터가 생성된다. 이 것도 로컬 영역이다. master를 동기화 하고자 한다면 master 브랜치를 받아야한다.

4. git branch master
5. git checkout master
6. merge

이때 remote branch 에서 데이터를 병합한다.

master만 가져오는 것은 `git clone`으로 해결할 수 있다. 다른 branch는 `git checkout -b dev origin/dev` 로 해결 할 수 있다.

만약 dev branch에 새로 commit이 생겼을때 remote branch에 동기화를 하려면 git fetch origin 을 해야 한다.

### blog-alone(개인) 실습

repository git clone(main), dev branch를 생성 -> setting_topic 생성 후 환경설정.txt 생성 커밋 -> dev branch 쪽에서 setting_topic merge 이때 Fast-forward 방식은 기본적으로 로그가 안 남기 때문에 --no-ff 옵션을 주어 로그 추가 -> dev branch에서 write, login topic branch를 생성 -> 글쓰기.txt, 로그인.txt 각각 브랜치에서 커밋 후 dev branch에서 --no-ff 옵션으로 merge -> 최종적으로 main쪽으로 이동하여 dev branch를 merge -> 로그가 너무 많기 때문에 master에서 git tag blog1.0.0 이라고 태그를 달아준다.(git tag blog1.0.0) 최종 확인을 위해 -> 이후 push origin main -> git push --tags origin main

topic은 추후에 삭제해줘도 되고 안해 줘도 된다.

### blog-team(팀) 실습 승인

**A 시작**
A 계정으로 repository git clone(main) -> main 환경설정.txt 생성 후 커밋 -> dev branch를 생성 -> git push --all -> 해당 저장소에 B 초대 -> 이후 dev, main branch의 ruleset 부여(push 할 수 있는 사람은 A 뿐) 

**B 시작**
B의 작업환경에서 A 레파지토리 git clone -> git checkout -b dev origin/dev(동기화) -> join_topic branch 생성 -> 회원가입.txt 생성 -> add . -> commit -> git push origin join_topic -> Github pull request를 A에게 요청

**A 시작**
Request pull 확인 -> Commit Approve (Comment는 draft의 경우 Request changes는 반려) -> Merge pull request

**B 시작**
git push --delete origin join_topic -> git checkout dev -> git pull origin dev / git fetch origin

main과 dev의 push는 A밖에 하지 못함. 만약 B라고 push 하려고 하면 거절당한다. B 입장에서 올리기 위해선 main과 dev를 동일하게 들고 있는다. 이후 topic branch를 만들어서 push를 하여 pull request 요청을 하여야 한다.

> draft
> 초안이라는 것 완료 되지 않았는데 코드 리뷰를 요청할 경우 즉, 중간 보고

### blog-team(팀) 실습 거절

**B시작**
login_topic branch 생성 -> 로그인.txt 생성 -> 2번에 거쳐 커밋(쓸데없는 메시지 포함) -> git push origin login_topic

**A시작**
pull request 반려

**B시작**
해당 로그 rebase -> git push 하면 형상이 안맞아서 오류가 남 (커밋 갯수가 다름) -f 옵션을 주어 강제 푸시 git push -f origin login_topic 혹은 login_topic2 새로 만들어서 해도 가능하지만 강제로 하는것 추천

**A시작**
프로젝트가 완료가 되었으니 dev 를 pull -> main에서 dev를 merge -> git tag blog1.0.0 -> git push --tags origin main (태그까지 포함해서 push)

**merge 순서 이해하기**

**시나리오 1**

환경설정.txt 생성 -> git add . -> git commit -m "환경설정" -> 토픽 생성 git checkout -b topic/login -> 로그인.txt 생성 -> git add . -> git commit -m "로그인" -> 동시에 개발하고 있다는 가정을 하기 위해 master branch로 돌아와 master branch 기준 토픽 생성 git checkout -b topic/join -> 회원가입.txt 생성 -> git add . -> git commit -m "회원가입" -> 회원가입이 먼저 끝났다고 가정하여 merge를 한다. git merge --no-ff topic/join -> 이후 로그인을 동일하게 merge -> 이떄 로그가 회원가입 => 로그인 순이 아닌 커밋된 시점인 로그인 => 회원가입 순으로 로그가 찍힌다.

merge의 history 순서는 각 topic에서 커밋이 된 시점의 영향을 받는다.

커밋을 영향을 받지 않게 merge가 되게 하려면? 즉, merge 순으로 로그가 찍히게 하려면 rebase를 사용하여야 한다.

**시나리오 2**

환경설정.txt 생성 -> git add . -> git commit -m "환경설정" -> 토픽 생성 git checkout -b topic/login -> 로그인.txt 생성 -> git add . -> git commit -m "로그인" -> 동시에 개발하고 있다는 가정을 하기 위해 master branch로 돌아와 master branch 기준 토픽 생성 git checkout -b topic/join -> 회원가입.txt 생성 -> git add . -> git commit -m "회원가입" -> 회원가입이 먼저 끝났다고 가정하여 merge를 한다. git merge --no-ff topic/join -> git checkout topic/login -> git rebase master -> git checkout master -> git merge --no-ff topic/login

리베이스를 하게되면 기준 브런치 (master) 의 최신 커밋 위로 재배치되게 된다.

## 대규모 협업하기 실습

**홍팀장**

lol-git-prac 레파지토리 생성 (README.md 포함) -> git clone `레파지토리 주소` -> lol-git-prac 이동 -> README.md 수정 -> 환경설정.txt 생성 후 add/commit -> git checkout -b dev -> git push --all -> 김대리와 최사원은 레파지토리 콜라보 추가 -> add rule 추가 -> 아무무 PR 확인 후 승인 dev 브런치로 merge -> 야스오 PR 확인 후 릴리즈 1.0 버전을 위해 승인하지 않고 comment 만 남김 -> 누누 PR 확인 후 승인 dev 브런치로 merge -> dev 브런치 동기화 (git pull origin dev) -> dev기준 release-1.0 브런치 생성 (git checkout -b release-1.0) -> 릴리즈 브런치 push (git push origin release-1.0) -> QA 진행중 이와 동시에 dev 브랜치에 야스오 PR 요청 온것을 거절하고 rebase 요청 merge시 커밋 순서가 꼬이기 때문에.. -> rebase 되어 다시 push 된 것을 확인 후 merge 승인 처리 -> release 1.0 main에서 merge 받음 -> 메인 쪽에서 git tag 1.0 -> git push --tags origin main 

김대리와 최사원이 동시 개발한다고 가정

**김대리**

git clone `레파지토리 주소` -> lol-git-prac 이동 -> git checkout -b dev origin/dev -> 개발 브런치 생성 git checkout -b feature/amumu -> 아무무.txt 생성 후 add/commit (개발완료) -> git push origin feature/amumu 로 PR 요청 -> 야스오 작업 시작 -> git checkout dev -> git pull origin dev -> git checkout -b feature/yasuo -> 야스오.txt 생성 후 add/commit (개발완료) -> git push origin feature/yasuo -> PR 요청 -> 승인 대기 -> rebase 하라고 거절된 것 확인 -> git checkout dev -> git pull origin dev -> git checkout feature/yasuo -> git rebase dev -> git push -f origin feature/yasuo (강제 푸쉬)

**최사원**

김대리보다 개발속도가 늦다고 가정

git clone `레파지토리 주소` -> lol-git-prac 이동 -> git checkout -b dev origin/dev -> 개발 브런치 생성 git checkout -b feature/nunu -> 누누10프로.txt 생성 (개발진행중) -> 누누100프로.txt 생성 후 add/commit -> git checkout dev -> git pull origin dev -> git checkout feature/nunu -> git rebase dev (머지 순서 맞추기) -> git push origin feature/nunu -> PR 요청

## fork

clone 받은 건 origin 원본은 upstream이라고 한다.

해당 레파지토리 fork -> git clone `레파지토리 주소` -> 내용 수정 -> git add . -> gi commit -m "내용 변경" -> git push origin master -> contribute -> PR 요청