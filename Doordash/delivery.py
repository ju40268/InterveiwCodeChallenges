def possile(l, all_possile):
    rest = list(set(all_possile) - set(l))
    tmp = []
    for r in rest:
        if 'p' in r:
            tmp.append(l+[r])
        if 'd' in r:
            idx = r[1]
            pick = 'p' + idx
            if pick not in rest:
                tmp.append(l+[r])
    return tmp


def interview(n):
    all_possile = []
    for i in range(1, n+1):
        all_possile.append("p{}".format(str(i)))
        all_possile.append("d{}".format(str(i)))

    q = []
    for i in range(1, n+1):
        q.append(["p{}".format(str(i))])
    res = []
    while q:
        l = q.pop(0)
        if len(l) == 2*n:
            res.append(l)
        else:
            q.extend(possile(l, all_possile))
    print(res)
    print(len(res))

if __name__ == '__main__':
    interview(2)