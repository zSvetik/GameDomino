package com.my.GameDomino.utils;

import java.util.*;

import com.my.GameDomino.entity.*;

public class Utils {

    private static final int MAX_POINT_ON_ONE_SIDE_DOMINO = 6;
    public static final ListDomino ALL_LIST_DOMINO = new ListDomino(createDominoList(MAX_POINT_ON_ONE_SIDE_DOMINO));
    private static ListDomino TMP_CHAIN = new ListDomino(Collections.nCopies(MAX_POINT_ON_ONE_SIDE_DOMINO, null));
    private static int END_ELEMENT = 0;
    private static ChainDomino INNER_CHAIN_DOMINO;

    public static String randomString() {
        return String.valueOf(UUID.randomUUID().getLeastSignificantBits());
    }

    public static List<Integer> getRandomCount(int count) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        if (count < 0) {
            count = random.nextInt(28);
        }
        for (int i = 0; ((i < count) && (count < 28)); i++) {
            // id доминошки
            int k = random.nextInt(28);
            if (!result.contains(k)) {
                result.add(k);
            }
        }
        return result;
    }

    private static List<Domino> createDominoList(int count) {
        List<Domino> result = new ArrayList<Domino>();
        int index = 0;
        for (int i = 0; i <= count; i++) {
            for (int j = i; j <= count; j++) {
                result.add(new Domino(index++, i, j));
            }
        }
        return result;
    }

    public static ChainDomino selectedListDomino(List<Integer> listId) {
        ChainDomino result = new ChainDomino();
        ListDomino listDomino = new ListDomino();
        for (Domino allDomino : ALL_LIST_DOMINO.getListDomino()) {
            if (listId.contains(allDomino.getId())) {
                allDomino.setUsed(true);
                listDomino.addDomino(allDomino);
            }
        }
        result.setStringListDomino(listDomino.toString());
        return result;
    }

    public static void deselectListDomino() {
        for (Domino allDomino : Utils.ALL_LIST_DOMINO.getListDomino()) {
            allDomino.setUsed(false);
        }
    }

    public static void fillAllChain(ChainDomino chainDomino) {
        INNER_CHAIN_DOMINO = chainDomino;

        for (int i = 0; i < MAX_POINT_ON_ONE_SIDE_DOMINO; i++) {
            makeChain(i);
        }
    }

    private static void makeChain(int left) {
        if (getElement(left, left).isUsed()) {
            Domino tmpDomino = getElement(left, left);
            tmpDomino.setUsed(false); // убираем костяшку
            TMP_CHAIN.setDomino(END_ELEMENT, tmpDomino);
            END_ELEMENT++;
            if (isExist(left)) {
                makeChain(left);
            } else {
                saveChain(TMP_CHAIN.getListDomino(), END_ELEMENT);
            }
            END_ELEMENT--;
            tmpDomino.setUsed(true); // возвращаем костяшку
        } else {
            for (int right = 0; right <= MAX_POINT_ON_ONE_SIDE_DOMINO; right++) {
                if (getElement(left, right).isUsed()) {
                    Domino tmpDomino = getElement(left, right);
                    tmpDomino.setUsed(false); // убираем костяшку
                    TMP_CHAIN.setDomino(END_ELEMENT, tmpDomino);
                    END_ELEMENT++;
                    if (isExist(right)) {
                        makeChain(right);
                    } else {
                        saveChain(TMP_CHAIN.getListDomino(), END_ELEMENT);
                    }
                    END_ELEMENT--;
                    tmpDomino.setUsed(true); // возвращаем костяшку
                }
            }
        }
    }

    private static Domino getElement(int left, int right) {
        for (Domino domino : ALL_LIST_DOMINO.getListDomino()) {
            if (domino.isThisElement(new Domino(left, right))) {
                return domino;
            }
        }
        return null;
    }

    // сущеуствует ли еще подходящие костяшки?
    private static boolean isExist(int left) {
        int i = 0;
        while ((i <= MAX_POINT_ON_ONE_SIDE_DOMINO) && (!getElement(left, i).isUsed())) {
            i++;
        }
        return i <= MAX_POINT_ON_ONE_SIDE_DOMINO;
    }

    private static void saveChain(List<Domino> tmpChain, int endElement) {
        ChainDominoHistory chainDominoHistory = new ChainDominoHistory("", String.valueOf(new ListDomino(tmpChain.subList(0, endElement))));
        if (!INNER_CHAIN_DOMINO.containsChainDominoHistory(chainDominoHistory)) {
            INNER_CHAIN_DOMINO.getListChainDominoHistory().add(chainDominoHistory);
        }
    }
}
