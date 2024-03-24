

   Napisz program, kt�ry b�dzie s�u�y� do zarz�dzania przez dewelopera sieci� osiedli mieszkaniowych. Deweloper mo�e posiada� wiele osiedli, a w ramach osiedli mo�e istnie� wiele blok�w, w kt�rych istnieje dowolna (sko�czona) liczba mieszka� do wynajmu. Ka�de pomieszczenie mo�e by� zamieszkane przez dowoln� liczb� os�b, o kt�rych informacje nale�y przechowywa� (obiekty klasy Person), jednak zawsze pierwsza osoba, kt�ra rozpoczyna najem mieszkania jest osob� odpowiedzialn� za op�aty dot. wynajmu.

   W ramach najmu mieszkania (tylko je�eli osoba najmuje mieszkanie w ramach osiedla) jest mo�liwo�� dodatkowego najmu zamkni�tego miejsca parkingowego, w ramach kt�rego mo�na przechowywa� pojazdy, ale r�wnie� r�ne przedmioty.

   Ka�de mieszkanie jak i miejsce parkingowe posiadaj� informacj� o swojej powierzchni u�ytkowej jak i unikalny numer identyfikacyjny, dzi�ki kt�remu mo�emy jednoznacznie zdefiniowa� obiekt symbolizuj�cy mieszkanie lub miejsce parkingowe.

   Najemca mo�e dowolnie zameldowywa� i wymeldowywa� osoby z mieszkania, jak i r�wnie�, je�li posiada miejsce parkingowe, to mo�e wk�ada� i wyjmowa� przedmioty oraz pojazdy. Ka�da osoba mo�e mie� wynaj�tych wiele mieszka� i miejsc parkingowych, o ile sumaryczna liczba wynajmowanych mieszka� i miejsc parkingowych przez dan� osob� (najemc�) nie jest wi�ksza ni� 5. Ka�de pomieszczenie mo�e mie� tylko jednego najemc� w jednym czasie.

   Nale�y zapewni�, aby identyfikatory pomieszcze� by�y unikalne oraz tworzone automatycznie podczas tworzenia obiektu typu Mieszkanie i MiejsceParkingowe. Rozmiar powierzchni u�ytkowej w przypadku oby wspomnianych typ�w pomieszcze� nale�y dostarcza� podczas tworzenia obiektu pomieszczenia.

Mo�liwe s� dwa sposoby wskazania rozmiaru pomieszczenia:

� poprzez podanie obj�to�ci w metrach sze�ciennych
� poprzez podanie rozmiar�w pomieszczenia w postaci d�ugo�ci, szeroko�ci i wysoko�ci pomieszczenia (dla uproszczenia zak�adamy, �e pomieszczenie jest prostopad�o�cianem. Przy tym podej�ciu powierzchnia u�ytkowa pomieszczenia zostaje wyliczona podczas tworzenia obiektu na podstawie przekazanych warto�ci).

   Pomieszczenie dla okre�lonego najemcy posiada r�wnie� dat� rozpocz�cia najmu oraz dat� zako�czenia najmu. Je�li data zako�czenia najmu si� przedawni�a, to zostaje wystosowane pismo

(obiekt typu File), kt�re zostaje zapisane do obiektu klasy Person definiuj�cego konkretnego najemc�.

   W zadaniu r�wnie� nale�y zaimplementowa� mechanizm up�ywaj�cego czasu za po�rednictwem w�tk�w. W�tek powinien co 5 sekund przesuwa� dat� o 1 dzie� do przodu, symuluj�c up�yw czasu. R�wnolegle powinny by� co 10 sekund sprawdzane kwestie wynajmu, czy wszystkie pomieszczenia nadal s� w trakcie najmu, czy mo�e wynajem pomieszczenia ju� usta�.

   Je�li najem zostanie odnowiony lub najem zostanie anulowany przez najemc�, pismo dot. zad�u�enia zostaje usuni�te z akt osoby.

   Je�li najemca nie odnowi najmu w przeci�gu 30 dni, nale�y wyczy�ci� pomieszczenie, kt�rego najem si� zako�czy�o, a pismo pozostaje w aktach.

W przypadku mieszkania przeprowadzamy eksmisj� os�b zamieszka�ych w tym mieszkaniu.

   W przypadku miejsca parkingowego, w pierwszej kolejno�ci sprawdzamy czy znajduje si� na miejscu parkingowym pojazd. Je�li tak, to w zwi�zku z przy�pieszon� decyzj� komornicz� zostaje on sprzedany (z punktu widzenia programu usuwamy obiekt z pomieszczenia), a koszt sprzeda�y pokrywa najem pomieszczenia na najbli�sze 2 miesi�ce. Je�li nie by�o pojazdu, to utylizacji podlegaj� wszystkie przechowywane rzeczy na miejscu parkingowym.

   Osoba posiada ponad wymienione wcze�niej informacje takie dane jak imi�, nazwisko, pesel, adres, dat� urodzenia.

   Je�li najem b�dzie chcia�a rozpocz�� osoba z wi�cej ni� trzema zad�u�eniami (co najmniej 3 obiekty typu File) na przestrzeni najm�w na tle ca�ego osiedla, rzucony powinien zosta� wyj�tek ProblematicTenantException z komunikatem � �Osoba X posiada�a ju� najem pomieszcze�: lista_pomieszczen�, gdzie X to imi� i nazwisko danej osoby, za� lista_pomieszczen definiuje wynajmowane pomieszczenia, kt�re zosta�y wynaj�te.

   Ka�dy z przedmiot�w i pojazd�w posiadaj� zapisan� informacj� o nazwie oraz polu powierzchni jak� zajmuje. Powierzchnia zajmowana przez przedmiot/pojazd mo�e by� dostarczona na dwa sposoby tak jak w przypadku pomieszczenia.

Pojazdy s� podzielone na rozr�niaj�ce je typy:

� samoch�d terenowy,
� samoch�d miejski,
� ��d�,
� motocykl,
� amfibia

   Ka�dy z pojazd�w poza nazw� oraz powierzchni� musi posiada� cechy charakterystyczne dla danego typu pojazdu (np. pojemno�� silnika, typ pojazdu, typ silnika itp.). Dla ka�dego typu pojazdu mog� powtarza� si� cechy charakterystyczne, jednak co najmniej jedna powinna by� r�na na tle innych pojazd�w.

   W przypadku wk�adania ka�dego przedmiotu lub pojazdu do pomieszczenia musimy si� upewni�, �e pomieszczenie jest w stanie pomie�ci� ten obiekt. Je�li tak nie jest, zostaje rzucony wyj�tek TooManyThingsException z komunikatem �Remove some old items to insert a new item�.

   Stan os�b zamieszkuj�cych osiedle wraz ze wszelkimi danymi dot. osoby, wynajmowanych pomieszcze�, przedmiot�w, itp. musi by� zapisywane po wybraniu z menu odpowiedniej funkcjonalno�ci. Informacje zapisane w pliku powinny by� zapisane przejrzy�cie i czytelnie dla cz�owieka z zachowaniem poni�szych regu�:

� Pomieszczenia powinny by� posortowane rosn�co wed�ug rozmiaru pomieszczenia.
� Zawarto�� pomieszczenia powinna by� posortowana malej�co wed�ug rozmiaru przedmiotu je�li jest taki sam to wed�ug nazwy.

Zasada dzia�ania programu:

� W metodzie main nale�y utworzy� osiedle wraz z co najmniej dziesi�cioma gotowymi pomieszczeniami r�nego typu i rozmiaru oraz kilka (minimum 5) gotowych os�b. Ze wst�pnie przydzielonymi najmami oraz umiejscowionymi przedmiotami na miejscach parkingowych.
� Po uruchomieniu programu, u�ytkownik powinien mie� mo�liwo�� za po�rednictwem konsoli polece� wywo�ania wszystkich wspomnianych funkcjonalno�ci. S� to m.in:

� zako�czenia programu w dowolnym momencie
� wybrania kt�r� jest osob� (nie jest potrzebne �adne logowanie, wystarczy wskazanie np. unikalnego numeru osoby)
� wypisania swoich danych ��cznie z wynaj�tymi pomieszczeniami
� wy�wietlenia wolnych pomieszcze�
� wynaj�cia nowego pomieszczenia, po uprzednim jego wybraniu
� wybrania pomieszczenia kt�re wynajmuje dana osoba oraz wy�wietlenia zawarto�ci pomieszczenia
� w�o�enia nowych pojazd�w/przedmiot�w pami�taj�c, aby nie przepe�ni� pomieszczenia
� wyj�cia przedmiot�w lub pojazd�w.
� wykonania polecenia zapisuj�cego aktualny stan osiedla do pliku





